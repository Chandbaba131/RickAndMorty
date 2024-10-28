package com.example.rickandmorty.domain.repository

import com.exmple.rickandmorty.GetCharacterDetailsByIdQuery

interface CharacterDetailsRepository {
    suspend fun getCharacterDetails(characterId: String): GetCharacterDetailsByIdQuery.Data?
}
