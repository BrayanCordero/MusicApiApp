package com.example.musicapiapp.rest

import com.example.musicapiapp.model.MusicResponse
import io.reactivex.Single
import javax.inject.Inject

interface MusicRepository {

    fun getAllClassicalMusic(): Single<MusicResponse>
}

class MusicRepositoryImpl @Inject constructor(
    private val serviceApi: MusicServiceApi
): MusicRepository{

    override fun getAllClassicalMusic(): Single<MusicResponse> {
        return serviceApi.getClassicalMusic()
    }

}