package com.example.musicapiapp.DI

import com.example.musicapiapp.presenter.AllClassicalPresenter
import com.example.musicapiapp.presenter.ClassicalMusicPresenterContract
import com.example.musicapiapp.presenter.ClassicalMusicViewContract
import dagger.Binds
import dagger.Module

@Module
abstract class PresentersModule {

    @Binds
    abstract fun providesAllClassicalPresenter(
        allClassicalPresenter: AllClassicalPresenter
    ): ClassicalMusicPresenterContract

    //add all presenter that will be needed in thi app here
}