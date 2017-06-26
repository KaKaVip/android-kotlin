package com.haipq.mini.weather.home

import com.haipq.mini.weather.data.source.CityRepositoryComponent
import com.haipq.mini.weather.util.ActivityScoped
import dagger.Component

/**
 * Created by haipq on 6/29/17.
 */
@ActivityScoped
@Component(dependencies = arrayOf(CityRepositoryComponent::class), modules = arrayOf(HomeModule::class))
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
}