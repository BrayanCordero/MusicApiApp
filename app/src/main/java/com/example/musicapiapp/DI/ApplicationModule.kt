package com.example.musicapiapp.DI

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.musicapiapp.database.MusicDAO
import com.example.musicapiapp.database.MusicDatabase
//import com.example.musicapiapp.ui.MIGRATION_1_2
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideRoomDb(context: Context): MusicDatabase =
        Room.databaseBuilder(
            context,
            MusicDatabase::class.java,
            "music-db"
        )
            //.addMigrations(MIGRATION_1_2)//add a migration here when making changes to DB
            //.addMigrations(MIGRATION_2_3) //need to add all migrations if there are multiple
            .build()

    @Provides
    fun providesMusicDAO(database: MusicDatabase): MusicDAO=
        database.getMusicDAO()
}