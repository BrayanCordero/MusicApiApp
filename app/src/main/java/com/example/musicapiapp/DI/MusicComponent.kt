package com.example.musicapiapp.DI

import com.example.musicapiapp.MainActivity
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        RestModule::class
    ]
)

interface MusicComponent {

    fun inject(mainActivity: MainActivity)
}