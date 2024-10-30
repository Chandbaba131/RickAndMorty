package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import com.example.presentation.HomesScreen
import com.example.presentation.viewmodel.CharactersViewModel
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject lateinit var viewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as RickAndMortyApplication).appComponent.inject(this)
        val activity =
            this as? ComponentActivity
                ?: throw IllegalStateException("ParentComposable must be called from an Activity")

        val appComponent = (activity.application as RickAndMortyApplication).appComponent
        val viewModelFactory = appComponent.viewModelFactory() // Get the ViewModelFactory
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                val viewModel: CharactersViewModel =
                    remember {
                        ViewModelProvider(activity, viewModelFactory)[CharactersViewModel::class.java]
                    }
                HomesScreen(viewModel)
            }
        }
    }
}
