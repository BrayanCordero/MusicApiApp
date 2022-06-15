package com.example.musicapiapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicapiapp.domain.DomainMusic
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MusicDAO {

    @Query("SELECT*FROM music_table")
    fun getAllMusic(): Single<List<DomainMusic>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertAllMusic(vararg music: DomainMusic): Completable

    @Query("SELECT*FROM music_table WHERE id LIKE:musicId")
    fun getMusicById(musicId: Int): Single<DomainMusic>
}