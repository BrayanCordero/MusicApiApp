package com.example.musicapiapp.database

import com.example.musicapiapp.domain.DomainMusic
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


interface LocalDataRepository{
    fun getAllClassicalMusic(): Single<List<DomainMusic>>
    fun insertMusic(music : List<DomainMusic>): Completable
    fun getMusicById(musicId: Int) : Single<DomainMusic>
    fun getAllRockMusic():Single<List<DomainMusic>>
    fun getAllPopMusic():Single<List<DomainMusic>>
}
class LocalDataRepositoryImpl @Inject constructor(
    private val musicDAO: MusicDAO
): LocalDataRepository {

    override fun getAllClassicalMusic(): Single<List<DomainMusic>> {
//        return musicDAO.getAllMusic()
        return musicDAO.getAllClassicalMusic()
    }

    override fun insertMusic(music: List<DomainMusic>): Completable {
        return musicDAO.insertAllMusic(*music.toTypedArray())
    }

    override fun getMusicById(musicId : Int): Single<DomainMusic> {
        return musicDAO.getMusicById(musicId)
    }

    override fun getAllRockMusic(): Single<List<DomainMusic>> {
        return musicDAO.getAllRockMusic()
    }

    override fun getAllPopMusic(): Single<List<DomainMusic>> {
        return musicDAO.getAllPopMusic()
    }
}