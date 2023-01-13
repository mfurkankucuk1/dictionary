package com.mfk.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import com.mfk.dictionary.databinding.ActivityMainBinding
import com.mfk.dictionary.viewModel.DictionaryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding :ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private val dictionaryViewMode:DictionaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}