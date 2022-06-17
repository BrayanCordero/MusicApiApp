package com.example.musicapiapp.DI

import com.example.musicapiapp.MainActivity
import com.example.musicapiapp.ui.ClassicalFragment
import com.example.musicapiapp.ui.PopFragment
import com.example.musicapiapp.ui.RockFragment
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        RestModule::class,
        PresentersModule::class,
        RepositoryModule::class
    ]
)

interface MusicComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(classicalFragment: ClassicalFragment)
    fun inject(rockFragment: RockFragment)
    fun inject(popFragment: PopFragment)
}