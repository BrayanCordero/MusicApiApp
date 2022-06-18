package com.example.musicapiapp.DI

import com.example.musicapiapp.presenter.*
import dagger.Binds
import dagger.Module

@Module
abstract class PresentersModule {

    @Binds
    abstract fun providesAllClassicalPresenter(
        allClassicalPresenter: AllClassicalPresenter
    ): ClassicalMusicPresenterContract

    @Binds
    abstract fun providesAllRockPresenter(
        allRockPresenter: AllRockPresenter
    ):RockMusicPresenterContract

    //add all presenter that will be needed in thi app here
}