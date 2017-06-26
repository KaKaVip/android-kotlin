/*
 *
 *  * Copyright  (c) 2017, Pham Quy Hai
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.haipq.mini.weather

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by haipq on 6/24/17.
 */
@Module
class NetworkModule(val apiUrl: String,
                    val apiKey: String,
                    val apiPinners: String) {

    companion object {
        const val OKHTTP_NO_CACHE = "okhttp_nocache"
        const val OKHTTP_CACHE = "okhttp_cache"
    }

    @Provides
    @Singleton
    fun provideHttpCache(context: Context): Cache {
        val cacheSize = 10L * 1024L * 1024L
        val cache = Cache(context.cacheDir, cacheSize)
        return cache
    }

    // Export pinner from domain using https://network-security.haipq.com
    @Provides
    @Singleton
    fun provideCertificatePinner(): CertificatePinner {
        val domain = apiUrl.replace("https://","").replace("http://","")
        val pinners = apiPinners.split(",")
        val builder = CertificatePinner.Builder()
        pinners.map { builder.add(domain, it) }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideBindApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("key", apiKey)
                    .build()
            val requestBuilder = original.newBuilder()
                    .url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    @Named(OKHTTP_CACHE)
    fun provideOkHttpClientCache(cache: Cache,
                            logging: HttpLoggingInterceptor,
                            bindApiKey: Interceptor,
                            pinner: CertificatePinner): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .certificatePinner(pinner)
                .addInterceptor(bindApiKey)
                .addInterceptor(logging)
                .build()
    }

    @Provides
    @Singleton
    @Named(OKHTTP_NO_CACHE)
    fun provideOkHttpClientNoCache(logging: HttpLoggingInterceptor,
                            bindApiKey: Interceptor,
                            pinner: CertificatePinner): OkHttpClient {
        return OkHttpClient.Builder()
                .certificatePinner(pinner)
                .addInterceptor(bindApiKey)
                .addInterceptor(logging)
                .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, @Named(NetworkModule.OKHTTP_CACHE) okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(apiUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

}