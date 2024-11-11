package com.example.presentation.characters

import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.presentation.uistate.UiState
import com.example.presentation.viewmodel.CharactersViewModel
import com.exmple.rickandmorty.GetCharactersQuery
import com.exmple.rickandmorty.fragment.Character
import com.exmple.rickandmorty.fragment.Location
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import usecases.CharacterUseCase

class CharactersViewModelTest {
    private var useCase: CharacterUseCase = mockk(relaxed = true)
    private lateinit var charactersViewModel: CharactersViewModel
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        charactersViewModel = CharactersViewModel(useCase)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testFetchCharacterUpdatesToUi() =
        runTest(testDispatcher) {
            val mockPagingData = flowOf(getCharacters())
            coEvery { useCase.invokeCharacters() } returns mockPagingData
            charactersViewModel.charactersState.test {
                charactersViewModel.fetchData()
                assertThat(awaitItem()).isEqualTo(UiState.Loading)
                val successState = awaitItem()
                assertThat(successState).isInstanceOf(UiState.Success::class.java)
                val expectedCharacters = mockPagingData.toList()
                val actualCharacters = flowOf((successState as UiState.Success).data).toList()
                assertThat(actualCharacters).isEqualTo(expectedCharacters)
                cancelAndConsumeRemainingEvents()
                advanceUntilIdle()
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun getCharacters(): PagingData<GetCharactersQuery.Result> {
        val items =
            listOf(
                GetCharactersQuery.Result(
                    "",
                    Character(
                        name = "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        location = Character.Location("", Location("", "", "")),
                    ),
                ),
                GetCharactersQuery.Result(
                    "",
                    Character(
                        name = "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        Character.Location(
                            "",
                            Location("", "", ""),
                        ),
                    ),
                ),
            )

        return PagingData.from(items)
    }

}