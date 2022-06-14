package com.example.musicapiapp.DI

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context = application.applicationContext
}