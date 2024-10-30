package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.exmple.rickandmorty.GetCharactersQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharactersTestRepository : com.example.domain.repository.CharactersRepository {
    override suspend fun getCharacters(): Flow<PagingData<GetCharactersQuery.Result>> {
        val items =
            listOf(
                GetCharactersQuery.Result(
                    "",
                    Character(
                        name = "",
                        "",
                        "",
                        Character.Location("", ""),
                    ),
                ),
                GetCharactersQuery.Result(
                    "",
                    Character(
                        name = "",
                        "",
                        "",
                        Character.Location("", ""),
                    ),
                ),
            )

        return flow { PagingData.from(items) }
    }
}
