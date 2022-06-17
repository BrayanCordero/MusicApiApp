package com.example.musicapiapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.musicapiapp.model.Music

@Entity(tableName = "music_table")
data class DomainMusic(
    @PrimaryKey
    val id: Int,
    val artistName: String,
    val trackId: Int,
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


    //add fields that you need here then below map it to the api fields.

)


//this is an extension function to map the network data to the domain data
// only Music Type will be able to call this method.
fun List<Music>.mapToDomainMusic():List<DomainMusic>{
    return this.map { networkMusic ->
        DomainMusic(
            id = networkMusic.artistId ?: 99999,
            artistName = networkMusic.artistName ?: "invalid name",
            albumCover = networkMusic.artworkUrl100 ?: "cover not available",
            releaseDate = networkMusic.releaseDate ?: "unknown",
            preview = networkMusic.previewUrl ?: "not available",
            trackId = networkMusic.trackId?: 99999,
            trackName = networkMusic.trackName?:"not available",
            trackPrice = networkMusic.trackPrice?: 9999.99,
            collectionPrice = networkMusic.collectionPrice?: 9999.99,
            collectionName = networkMusic.collectionName?:"unknown",
            country = networkMusic.country?: "not available",
            trackNumber = networkMusic.trackNumber?: 99999,
            trackCount = networkMusic.trackCount?:9999999
        )
    }
}


