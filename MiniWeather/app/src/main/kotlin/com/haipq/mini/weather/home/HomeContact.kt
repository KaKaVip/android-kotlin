package com.haipq.mini.weather.home

import com.haipq.mini.weather.BaseView
import com.haipq.mini.weather.BaseViewModel
import com.haipq.mini.weather.data.City

/**
 * Created by haipq on 6/29/17.
 */

interface HomeContact {

    interface OnSelectedCityCallback {
        fun onSelected(city: City)
        fun onCancel()
    }

    interface View : BaseView {
        fun test(data: String?)
        fun showLoading()
        fun hiddenLoading()
        fun showSelectCity(data: List<City>, callback: OnSelectedCityCallback)
    }

    interface ViewModel : BaseViewModel {
        fun create()
        fun search()
    }
}