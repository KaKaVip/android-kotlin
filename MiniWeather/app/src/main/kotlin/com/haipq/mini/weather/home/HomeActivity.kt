package com.haipq.mini.weather.home

import android.app.ProgressDialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import com.haipq.mini.weather.MiniWeatherApp
import com.haipq.mini.weather.R
import com.haipq.mini.weather.data.City
import com.haipq.mini.weather.databinding.HomeActivityBinding
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), HomeContact.View {

    @Inject lateinit var viewModel: HomeViewModel

    lateinit var loading: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewBinding = DataBindingUtil.setContentView<HomeActivityBinding>(this, R.layout.home_activity)

        DaggerHomeComponent.builder()
                .homeModule(HomeModule(this))
                .cityRepositoryComponent(MiniWeatherApp.cityRepositoryComponent)
                .build()
                .inject(this)

        viewBinding.viewModel = viewModel

        loading = ProgressDialog(this)
        loading.setTitle("Loading...")
        loading.hide()

        viewModel.create()

    }

    override fun showSelectCity(data: List<City>, callback: HomeContact.OnSelectedCityCallback) {
        if (data.isEmpty()) {
            callback.onCancel()
            //Toast.makeText(this, "City not found!", Toast.LENGTH_SHORT).show()
            return
        }
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Select City")
        alertBuilder.setCancelable(false)

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice)
        arrayAdapter.addAll(data.map { it.name })

        alertBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            callback.onCancel()
        }

        alertBuilder.setAdapter(arrayAdapter) { dialog, which ->
            dialog.dismiss()
            callback.onSelected(data.filter { city -> city.name.equals(arrayAdapter.getItem(which)) }.first())
        }
        alertBuilder.create().show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }

    override fun test(str: String?) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        loading.show()
        hiddenKeyboard()
    }

    override fun hiddenLoading() {
        loading.cancel()
    }


    override fun onDestroy() {
        viewModel.destroy()
        super.onDestroy()
    }

    private fun hiddenKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}
