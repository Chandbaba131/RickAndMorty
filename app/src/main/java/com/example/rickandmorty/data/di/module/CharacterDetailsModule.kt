package com.example.rickandmorty.data.di.module

import com.apollographql.apollo.ApolloClient
import com.example.rickandmorty.data.repository.CharacterDetailsRepositoryImpl
import com.example.rickandmorty.domain.repository.CharacterDetailsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CharacterDetailsModule {
    @Provides
    @Singleton
    fun provideCharacterDetails(apolloClient: ApolloClient): CharacterDetailsRepository = CharacterDetailsRepositoryImpl(apolloClient)
}
