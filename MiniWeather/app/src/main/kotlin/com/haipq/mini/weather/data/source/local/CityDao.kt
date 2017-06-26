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
import com.haipq.mini.weather.data.City
import io.reactivex.Flowable

/**
 * Created by haipq on 6/23/17.
 */

@Dao
interface CityDao {
    @Query("SELECT * FROM City ORDER BY name ASC")
    fun getAll(): Flowable<List<City>>

    @Query("SELECT * FROM City WHERE id = :p0")
    fun get(id: String): Flowable<City>

    @Update
    fun updateAll(vararg city: City)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg city: City)

    @Delete
    fun delete(city: City)

    @Query("DELETE FROM City")
    fun deleteAll()
}
