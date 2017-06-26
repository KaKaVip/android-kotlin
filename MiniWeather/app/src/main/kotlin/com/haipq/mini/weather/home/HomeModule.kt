package com.haipq.mini.weather.home

import dagger.Module
import dagger.Provides

/**
 * Created by haipq on 6/29/17.
 */
@Module
class HomeModule(val view: HomeContact.View) {

    @Provides
    fun provideContactView(): HomeContact.View {
        return view
    }
}