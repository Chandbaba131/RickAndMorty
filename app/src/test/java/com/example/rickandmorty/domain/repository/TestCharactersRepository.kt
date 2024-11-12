package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.exmple.rickandmorty.GetCharactersQuery
import com.exmple.rickandmorty.fragment.Character
import com.exmple.rickandmorty.fragment.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestCharactersRepository {
    fun getCharacters(): Flow<PagingData<GetCharactersQuery.Result>> {
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
                        Character.Origin("", ""),
                        episode = listOf(Character.Episode("", "", "")),
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
                        Character.Origin("", ""),
                        episode = listOf(Character.Episode("", "", "")),
                    ),
                ),
            )

        return flow { PagingData.from(items) }
    }
}
