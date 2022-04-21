package com.github.beleavemebe.moviesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.beleavemebe.moviesapp.R
import com.github.beleavemebe.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
