package com.darabi.mohammad.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.darabi.mohammad.movies.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //API Read Access Token (v4 auth)      eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMzdiZDg3NDNkNDJjOTdlOTYwYzU4MzRhM2YxZjAyOSIsInN1YiI6IjVmNjBlOTY5M2Y3ZTFkMDAzNzk0MGFkNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Q66kF5JkOEexCxfQ48s6AagWW0qelB0fOlw0r7_IO_0
    }
}