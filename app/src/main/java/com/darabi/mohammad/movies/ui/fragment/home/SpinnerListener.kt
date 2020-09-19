package com.darabi.mohammad.movies.ui.fragment.home

import android.util.Log
import android.view.View
import android.widget.AdapterView
import javax.inject.Inject

class SpinnerListener @Inject constructor() : OnSpinnerItemSelectedListener() {

    lateinit var callback: Callback

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        callback.onYearClick(parent?.getItemAtPosition(position).toString())
    }

    interface Callback {

        fun onYearClick(year: String)
    }
}