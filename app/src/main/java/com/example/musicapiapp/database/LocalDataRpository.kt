package com.example.musicapiapp.database

import com.example.musicapiapp.domain.DomainMusic
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


interface LocalDataRepository{
    fun getAllClassicalMusic(): Single<List<DomainMusic>>
    fun insertMusic(music : List<DomainMusic>): Completable
    fun getMusicById(musicId: Int) : Single<DomainMusic>
}
class LocalDataRepositoryImpl @Inject constructor(
    private val musicDAO: MusicDAO
): LocalDataRepository {

    override fun getAllClassicalMusic(): Single<List<DomainMusic>> {
        return musicDAO.getAllMusic()
    }

    override fun insertMusic(music: List<DomainMusic>): Completable {
        return musicDAO.insertAllMusic(*music.toTypedArray())
    }

    override fun getMusicById(musicId : Int): Single<DomainMusic> {
        return musicDAO.getMusicById(musicId)
    }
}