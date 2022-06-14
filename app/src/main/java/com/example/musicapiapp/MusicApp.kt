package com.example.musicapiapp

import android.app.Application
import com.example.musicapiapp.DI.DaggerMusicComponent

class MusicApp: Application() {
    override fun onCreate() {
        super.onCreate()

    }

    companion object{
        lateinit var component : DaggerMusicComponent
    }
}