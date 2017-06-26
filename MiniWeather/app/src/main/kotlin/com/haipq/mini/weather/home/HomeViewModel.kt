package com.haipq.mini.weather.home

import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.util.Log
import com.haipq.mini.weather.data.City
import com.haipq.mini.weather.data.source.CityRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * Created by haipq on 6/29/17.
 */
class HomeViewModel @Inject constructor(
        val cityRepository: CityRepository,
        val view: HomeContact.View
) : BaseObservable(), HomeContact.ViewModel {

    private val TAG = HomeViewModel::class.simpleName

    private lateinit var mSubscriptions: CompositeDisposable

    companion object {
        val searchText = ObservableField<String>()
    }

    override fun create() {
        mSubscriptions = CompositeDisposable()
    }

    override fun start() {
    }

    override fun search() {
        Log.d(TAG, "Search: ${searchText.get()}")
        val query: String? = searchText.get()
        if (query != null && query.isNotEmpty()) {
            mSubscriptions.clear()
            mSubscriptions.add(cityRepository.searchByName(query)
                    .doOnSubscribe { view.showLoading() }
                    .doOnComplete { view.hiddenLoading() }
                    .doOnError { view.hiddenLoading() }
                    .onErrorReturnItem(listOf())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { cities ->
                        view.showSelectCity(cities, object : HomeContact.OnSelectedCityCallback {
                            override fun onSelected(city: City) {
                                Log.d(TAG, "onSelected " + city.name)
                                view.test(city.name)
                            }

                            override fun onCancel() {
                                Log.d(TAG, "onCancel ")
                            }

                        })
                    })
        }
    }


    override fun destroy() {
        mSubscriptions.clear()
    }

}

