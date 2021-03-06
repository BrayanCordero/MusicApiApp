package com.example.musicapiapp.rest

import com.example.musicapiapp.model.MusicResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicServiceApi {

    @GET(POP_PATH)
    fun getAllClassicalMusic(
        @Query("term") term: String = "classical"
    ): Single<MusicResponse>

    @GET(POP_PATH)
    fun getAllRockMusic(
        @Query("term") term: String = "rock"
    ):Single<MusicResponse>

    @GET(POP_PATH)
    fun getAllPopMusic(
        @Query("term") term: String = "pop"
    ):Single<MusicResponse>

    companion object{
        const val BASE_URL = "https://itunes.apple.com/"
        private const val CLASSICAL_PATH = "search?term=classical&amp"
        private const val ROCK_PATH ="search?term=rock&amp"
        private const val POP_PATH = "search"

    }
}