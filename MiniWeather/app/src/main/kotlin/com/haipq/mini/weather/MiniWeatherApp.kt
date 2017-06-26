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

import android.support.multidex.MultiDexApplication
import com.haipq.mini.weather.data.source.CityRepositoryComponent
import com.haipq.mini.weather.data.source.DaggerCityRepositoryComponent

/**
 * Created by haipq on 6/23/17.
 */
class MiniWeatherApp : MultiDexApplication() {

    companion object {
        lateinit var cityRepositoryComponent: CityRepositoryComponent
    }

    override fun onCreate() {
        super.onCreate()

        val appModule = ApplicationModule(this)
        val networkModule = NetworkModule(getString(R.string.API_URL),
                                          getString(R.string.API_KEY),
                                          getString(R.string.API_CERTIFICATE_PINNERS))
        cityRepositoryComponent = DaggerCityRepositoryComponent.builder()
                .applicationModule(appModule)
                .networkModule(networkModule)
                .build()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

}