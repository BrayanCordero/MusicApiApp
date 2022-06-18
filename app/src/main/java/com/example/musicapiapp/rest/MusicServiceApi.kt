package com.example.musicapiapp.rest

import com.example.musicapiapp.model.MusicResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MusicServiceApi {

    @GET(CLASSICAL_PATH)
    fun getAllClassicalMusic(): Single<MusicResponse>

    companion object{
        const val BASE_URL = "https://itunes.apple.com/"
        private const val CLASSICAL_PATH = "search?term=classical&amp"
//        private const val ROCK_PATH =

    }
}