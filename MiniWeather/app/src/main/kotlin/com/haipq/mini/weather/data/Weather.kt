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

package com.haipq.mini.weather.data

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey


/**
 * Created by haipq on 6/23/17.
 */
@Entity(foreignKeys = arrayOf(ForeignKey(
                                entity = City::class,
                                parentColumns = arrayOf("id"),
                                childColumns = arrayOf("city_id"),
                                onDelete = ForeignKey.CASCADE)))
data class Weather(
        @PrimaryKey
        @Transient val city_id: String,
        val temp_c: Double,
        val temp_f: Double,
        val wind_mph: Double,
        val wind_kph: Double,
        val wind_degree: Int,
        val wind_dir: String,
        val humidity: Double,
        val cloud: Int,
        val feelslike_c: Double,
        val feelslike_f: Double,
        @Embedded val condition: Condition
)

