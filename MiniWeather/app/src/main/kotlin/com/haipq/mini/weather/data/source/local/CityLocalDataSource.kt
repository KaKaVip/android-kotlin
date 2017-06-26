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

package com.haipq.mini.weather.data.source.local

import com.haipq.mini.weather.data.City
import com.haipq.mini.weather.data.source.CityDataSource
import io.reactivex.Observable
import javax.inject.Singleton

/**
 * Created by haipq on 6/25/17.
 */
@Singleton
class CityLocalDataSource(val cityDao: CityDao) : CityDataSource {

    override fun searchByName(name: String): Observable<List<City>> {
        TODO("Local no need")
    }

    override fun getAll(): Observable<List<City>> {
        return cityDao.getAll().toObservable()
    }

    override fun get(id: String): Observable<City> {
        return cityDao.get(id).toObservable()
    }

    override fun insert(city: City) {
        cityDao.insertAll(city)
    }

    override fun delete(city: City) {
        cityDao.delete(city)
    }

    override fun deleteAll() {
        cityDao.deleteAll()
    }

    override fun refreshAll() {
        TODO("Local no need")
    }

}