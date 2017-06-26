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

import android.arch.persistence.room.*
import com.haipq.mini.weather.data.Weather
import io.reactivex.Flowable

/**
 * Created by haipq on 6/23/17.
 */
@Dao
interface WeatherDao {
    @Query("SELECT * FROM Weather")
    fun getAll(): Flowable<List<Weather>>

    @Query("SELECT * FROM Weather WHERE city_id = :p0")
    fun getByCityId(cityId: String): Flowable<Weather>

    @Update
    fun updateAll(vararg weather: Weather)

    @Insert
    fun insertAll(vararg weather: Weather)

    @Delete
    fun delete(weather: Weather)
}