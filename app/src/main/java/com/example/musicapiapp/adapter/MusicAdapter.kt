package com.example.musicapiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicapiapp.databinding.MusicItemBinding
import com.example.musicapiapp.domain.DomainMusic
import com.example.musicapiapp.model.Music




class MusicAdapter(
    private val onMusicItemClick: MusicItemClick,
    private val musicDataSet: MutableList<DomainMusic> = mutableListOf()

): RecyclerView.Adapter<MusicViewHolder>(){

    fun updateMusic(newMusic:List<DomainMusic>){
        musicDataSet.addAll(newMusic)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(
            MusicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) =
        holder.bind(musicDataSet[position],onMusicItemClick)

    override fun getItemCount(): Int = musicDataSet.size

}

class MusicViewHolder(
    private val binding: MusicItemBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(music: DomainMusic, onMusicItemClick: MusicItemClick){
        binding.artisName.text = music.artistName
        binding.trackName.text = music.trackName
        binding.trackPrice.text = music.trackPrice.toString()



        //getting the image from url and binding it to the cover
        Glide.with(binding.root)
            .load(music.albumCover)
            .override(300,300)
            .into(binding.albumCover)
    }
}

interface MusicItemClick{
    fun onMusicClicked(music:Music)
}