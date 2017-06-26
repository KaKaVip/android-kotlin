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

import com.haipq.mini.weather.data.City
import io.reactivex.Observable

/**
 * Created by haipq on 6/25/17.
 */
interface CityDataSource {

    fun searchByName(name: String): Observable<List<City>>

    fun insert(city: City)

    fun getAll(): Observable<List<City>>

    fun get(id: String): Observable<City>

    fun delete(city: City)

    fun deleteAll()

    fun refreshAll()
}