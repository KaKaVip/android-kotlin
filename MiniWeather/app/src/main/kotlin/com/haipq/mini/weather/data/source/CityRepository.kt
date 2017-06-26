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
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by haipq on 6/25/17.
 */
@Singleton
class CityRepository @Inject constructor(
        @Local var localData: CityDataSource,
        @Remote var remoteData: CityDataSource
) : CityDataSource {

    var cacheCities: MutableMap<String, City> = mutableMapOf()

    override fun searchByName(name: String): Observable<List<City>> {
        return remoteData.searchByName(name)
    }

    override fun getAll(): Observable<List<City>> {
        if (cacheCities.isNotEmpty()) {
            return Observable.just(cacheCities.values.toList())
        } else {
            return localData.getAll().flatMap { data ->
                data.map { cacheCities.put(it.id, it) }
                Observable.just(cacheCities.values.toList())
            }
        }
    }

    override fun get(id: String): Observable<City> {
        if (cacheCities.isNotEmpty() && cacheCities.containsKey(id)) {
            return Observable.just(cacheCities[id])
        } else {
            return localData.get(id).flatMap { city ->
                cacheCities[id] = city
                Observable.just(city)
            }
        }
    }

    override fun insert(city: City) {
        localData.insert(city)
        cacheCities[city.id] = city
    }

    override fun delete(city: City) {
        localData.delete(city)
        if (cacheCities.containsKey(city.id))
            cacheCities.remove(city.id)
    }

    override fun deleteAll() {
        localData.deleteAll()
        refreshAll()
    }

    override fun refreshAll() {
        cacheCities.clear()
    }

}