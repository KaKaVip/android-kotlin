package com.haipq.mini.weather

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.Fragment


/**
 * Created by haipq on 6/29/17.
 */


class ViewModelHolder<VM> : Fragment() {

    private var viewModel: VM = null!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun getViewModel(): VM {
        return viewModel
    }

    fun setViewModel(viewModel: VM) {
        this.viewModel = viewModel
    }


    companion object {

        fun <M> createContainer(@NonNull viewModel: M): ViewModelHolder<*> {
            val viewModelContainer = ViewModelHolder<M>()
            viewModelContainer.setViewModel(viewModel)
            return viewModelContainer
        }
    }
}