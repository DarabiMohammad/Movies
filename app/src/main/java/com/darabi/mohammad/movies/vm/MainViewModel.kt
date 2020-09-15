package com.darabi.mohammad.movies.vm

import androidx.lifecycle.ViewModel
import com.darabi.mohammad.movies.repository.Repository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
}