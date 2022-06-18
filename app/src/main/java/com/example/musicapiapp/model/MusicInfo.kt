package com.example.musicapiapp.model

import java.io.Serializable

data class MusicInfo(

    val artistName: String,
    val trackName: String,
    val albumCover: String,
    val releaseDate: String,
    val preview : String,
    val trackPrice: Double,
    val collectionPrice : Double,
    val collectionName: String,
    val country: String,
    val trackNumber: Int,
    val trackCount: Int
):Serializable
