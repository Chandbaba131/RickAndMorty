package com.example.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import com.example.presentation.characters.AllCharacters
import com.example.presentation.uistate.UiState
import com.exmple.rickandmorty.GetCharactersQuery
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    allCharacters: StateFlow<UiState<PagingData<GetCharactersQuery.Result>>>,
    topBarTitle: String,
    onNavigateToCharacterDetails: (String) -> Unit,
    onRefresh: (Boolean) -> Unit
) {
    val refreshState = rememberPullToRefreshState()
    val uiState by remember { allCharacters }.collectAsState()
    PullToRefreshBox(
        isRefreshing = uiState is UiState.Loading, state = refreshState,
        onRefresh = {
            onRefresh.invoke(true)
        }) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { RickAndMortyAppBar(topBarTitle) },
            content = { innerPadding ->
                AllCharacters(
                    uiState = uiState,
                    Modifier.padding(innerPadding),
                    onNavigateToCharacterDetails = onNavigateToCharacterDetails
                )
            }
        )
    }
}
