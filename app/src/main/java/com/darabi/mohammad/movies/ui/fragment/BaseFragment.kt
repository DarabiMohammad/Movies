package com.darabi.mohammad.movies.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.darabi.mohammad.movies.vm.MainViewModel
import kotlin.properties.Delegates

abstract class BaseFragment : Fragment() {

    protected val viewModel: MainViewModel by viewModels({ requireActivity() })
    protected open val layoutRes by Delegates.notNull<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(layoutRes, container, false)
}