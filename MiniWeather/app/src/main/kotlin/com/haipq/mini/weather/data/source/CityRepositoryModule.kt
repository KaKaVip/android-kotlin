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

package com.haipq.mini.weather.data.source

import com.haipq.mini.weather.data.source.local.AppDatabase
import com.haipq.mini.weather.data.source.local.CityLocalDataSource
import com.haipq.mini.weather.data.source.remote.ApiXuService
import com.haipq.mini.weather.data.source.remote.CityRemoteDataSource
import dagger.Module
import dagger.Provides

/**
 * Created by haipq on 6/25/17.
 */
@Module
class CityRepositoryModule {

    @Provides
    @Local
    fun provideLocalDataSource(appDatabase: AppDatabase): CityDataSource {
        return CityLocalDataSource(appDatabase.cityDao())
    }

    @Provides
    @Remote
    fun provideRemoteDataSource(api: ApiXuService): CityDataSource {
        return CityRemoteDataSource(api)
    }

}