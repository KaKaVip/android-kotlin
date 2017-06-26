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

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.haipq.mini.weather.data.City
import com.haipq.mini.weather.data.Condition
import com.haipq.mini.weather.data.Weather
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by haipq on 6/23/17.
 */
@RunWith(AndroidJUnit4::class)
class AppDatabaseInsTest {
    lateinit var weatherDao: WeatherDao
    lateinit var cityDao: CityDao

    lateinit var appDatabase: AppDatabase

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getTargetContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        weatherDao = appDatabase.weatherDao()
        cityDao = appDatabase.cityDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun create_city() {
        val hanoi = City("hn123", "Hanoi", "Vietnam", 12.45, 34.4)
        val hcm = City("hcm123", "Ho Chi Minh", "Vietnam", 12.45, 34.4)
        cityDao.insertAll(hanoi, hcm)
        cityDao.getAll().subscribe { cities ->
            assertThat(cities).isNotNull()
            assertThat(cities.size).isEqualTo(2)
        }
    }

    @Test
    fun update_city() {
        val hanoi = City("hn123", "Hanoi", "Vietnam", 12.45, 34.4)
        cityDao.insertAll(hanoi)
        val hanoi2 = hanoi.copy(name = "Hanoi2")
        cityDao.updateAll(hanoi2)
        cityDao.get(hanoi.id).subscribe { city ->
            assertThat(city).isNotNull()
            assertThat(city.name).isEqualTo(hanoi2.name)
        }
    }

    @Test
    fun create_weather() {
        val hanoi = City("hn123", "Hanoi", "Vietnam", 12.45, 34.4)
        cityDao.insertAll(hanoi)
        val weather = Weather(
                city_id = "hn123",
                cloud = 1,
                condition = Condition("test", "test", 0),
                feelslike_c = 1.3,
                feelslike_f = 1.3,
                humidity = 1.3,
                temp_c = 1.3,
                temp_f = 1.3,
                wind_degree = 1,
                wind_dir = "test",
                wind_kph = 1.3,
                wind_mph = 1.3
        )
        weatherDao.insertAll(weather)
        weatherDao.getByCityId("hn123").subscribe { weather ->
            assertThat(weather).isNotNull()
            assertThat(weather.condition.text).isEqualTo("test")
        }
    }

    @Test
    fun if_delete_city_auto_delete_weather_of_city() {
        val hanoi = City("hn123", "Hanoi", "Vietnam", 12.45, 34.4)
        cityDao.insertAll(hanoi)
        val weather = Weather(
                city_id = "hn123",
                cloud = 1,
                condition = Condition("test", "test", 0),
                feelslike_c = 1.3,
                feelslike_f = 1.3,
                humidity = 1.3,
                temp_c = 1.3,
                temp_f = 1.3,
                wind_degree = 1,
                wind_dir = "test",
                wind_kph = 1.3,
                wind_mph = 1.3
        )
        weatherDao.insertAll(weather)
        cityDao.delete(hanoi)
        weatherDao.getByCityId("hn123").subscribe { weather ->
            assertThat(weather).isNull()
        }
    }
}