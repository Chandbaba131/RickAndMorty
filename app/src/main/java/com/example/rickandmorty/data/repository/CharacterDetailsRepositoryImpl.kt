package com.example.rickandmorty.data.repository

import com.apollographql.apollo.ApolloClient
import com.example.rickandmorty.domain.repository.CharacterDetailsRepository
import com.exmple.rickandmorty.GetCharacterDetailsByIdQuery
import javax.inject.Inject

class CharacterDetailsRepositoryImpl
    @Inject
    constructor(
        val apolloClient: ApolloClient,
    ) : CharacterDetailsRepository {
        override suspend fun getCharacterDetails(characterId: String): GetCharacterDetailsByIdQuery.Data? =
            apolloClient.query(GetCharacterDetailsByIdQuery(characterId)).execute().data
    }
