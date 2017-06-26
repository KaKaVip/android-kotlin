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

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.mock.NetworkBehavior







/**
 * Created by haipq on 6/25/17.
 */
@RunWith(AndroidJUnit4::class)
class ApiXuServiceInsTest {

    lateinit var retrofit: Retrofit
    lateinit var apiXuService: ApiXuService

    @Before
    fun createMockServer() {
        val context = InstrumentationRegistry.getTargetContext()
        val behavior = NetworkBehavior.create()


    }

    @After
    fun stopMockServer() {

    }


}