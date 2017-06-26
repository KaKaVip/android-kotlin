package com.haipq.mini.weather.util

import com.haipq.mini.weather.common.retrofit.RetrofitException

/**
 * Created by haipq on 6/29/17.
 */
interface OnErrorHandling<T>{
    fun onError(error: RetrofitException, t: T)
}