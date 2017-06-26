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

import android.arch.persistence.room.ColumnInfo

/**
 * Created by haipq on 6/23/17.
 */
data class Condition(
        @ColumnInfo(name = "condition_text") val text: String,
        @ColumnInfo(name = "condition_icon") val icon: String,
        @ColumnInfo(name = "condition_code")val code: Int
)