package com.example.musicapiapp.DI

import com.example.musicapiapp.database.LocalDataRepository
import com.example.musicapiapp.database.LocalDataRepositoryImpl

import com.example.musicapiapp.rest.MusicRepository
import com.example.musicapiapp.rest.MusicRepositoryImpl

import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

//    @Binds
//    abstract fun provideMusicRepository(musicRepositoryImpl: MusicRepositoryImpl): MusicRepository

    @Binds
    abstract fun providesLocalRepository(
        localDataRepositoryImpl: LocalDataRepositoryImpl
    ): LocalDataRepository

    @Binds
    abstract fun providesNetworkRepository(
        networkRepositoryImpl: MusicRepositoryImpl
    ): MusicRepository
}
