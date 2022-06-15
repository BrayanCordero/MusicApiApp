package com.example.musicapiapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.musicapiapp.model.Music

@Entity(tableName = "music_table")
data class DomainMusic(
    @PrimaryKey
    val id: Int,
    val name: String
    //add fields that you need here then below map it to the api fields.

)


//this is an extension function to map the network data to the domain data
// only Music Type will be able to call this method.
fun List<Music>.mapToDomainMusic():List<DomainMusic>{
    return this.map { networkMusic ->
        DomainMusic(
            id = networkMusic.artistId ?: 99999,
            name = networkMusic.artistName ?: "invalid name"
        )
    }
}


