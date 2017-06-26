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

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by haipq on 6/24/17.
 */
class CityTest {
    @Test
    fun object_test() {
        val test = City("test","test","test",1.0,1.0)
        assertEquals(test.id, "test")
        assertEquals(test.name, "test")
        assertEquals(test.country, "test")
        assertEquals(test.lat, 1.0,0.0)
        assertEquals(test.lon, 1.0,0.0)
    }
}
