package com.example.musicapiapp.DI

import com.example.musicapiapp.presenter.AllClassicalPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class PresentersModule {

    @Binds
    abstract fun providesAllClassicalPresenter(
        allClassicalPresenter: AllClassicalPresenter
    ):AllClassicalPresenter

    //add all presenter that will be needed in thi app here
}