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
import com.haipq.mini.weather.data.source.local.AppDatabase
import com.haipq.mini.weather.data.source.local.AppDatabaseConfiguration
import com.haipq.mini.weather.data.source.remote.ApiXuService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by haipq on 6/24/17.
 */
@Module
class ApplicationModule(val app: MiniWeatherApp) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return app
    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabaseConfiguration.builder(context)
    }

    @Provides
    @Singleton
    fun provideApiXuService(retrofit: Retrofit): ApiXuService {
        return retrofit.create(ApiXuService::class.java)
    }
}