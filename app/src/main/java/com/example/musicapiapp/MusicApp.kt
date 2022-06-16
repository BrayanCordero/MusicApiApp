package com.example.musicapiapp

import android.app.Application
import com.example.musicapiapp.DI.ApplicationModule
import com.example.musicapiapp.DI.DaggerMusicComponent
import com.example.musicapiapp.DI.MusicComponent

class MusicApp: Application() {
    override fun onCreate() {
        super.onCreate()
    component = DaggerMusicComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()
    }

    companion object{
        lateinit var component : MusicComponent
    }
}