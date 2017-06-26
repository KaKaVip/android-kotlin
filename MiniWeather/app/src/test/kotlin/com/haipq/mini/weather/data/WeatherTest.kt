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

import org.junit.Assert
import org.junit.Test

/**
 * Created by haipq on 6/24/17.
 */
class WeatherTest {
    @Test
    fun object_test() {
        val test = Weather("test",1.0,1.0,1.0,1.0,1,"test",1.0,1,1.0, 1.0, Condition("test","test",1))
        Assert.assertEquals(test.city_id, "test")
        Assert.assertEquals(test.temp_c, 1.0, 0.0)
        Assert.assertEquals(test.temp_f, 1.0, 0.0)
        Assert.assertEquals(test.wind_mph, 1.0, 0.0)
        Assert.assertEquals(test.wind_kph, 1.0, 0.0)
        Assert.assertEquals(test.wind_degree, 1)
        //....
        Assert.assertEquals(test.condition.text, "test")
        //...
    }
}