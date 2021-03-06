package com.example.musicapiapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicapiapp.domain.DomainMusic

@Database(
    entities = [DomainMusic::class],
    version = 1
)
abstract class MusicDatabase: RoomDatabase() {

    abstract fun getMusicDAO(): MusicDAO
}