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

package com.haipq.mini.weather.data.source.remote

import com.haipq.mini.weather.data.City
import com.haipq.mini.weather.data.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by haipq on 6/24/17.
 */
interface ApiXuService {
    @GET("/v1/search.json")
    fun searchCityByName(@Query("q") cityName: String): Observable<List<City>>

    @GET("/v1/current.json")
    fun getWeatherByCityName(@Query("q") cityName: String): Observable<List<Weather>>
}