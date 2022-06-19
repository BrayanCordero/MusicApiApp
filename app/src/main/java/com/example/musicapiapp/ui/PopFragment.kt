package com.example.musicapiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapiapp.MusicApp
import com.example.musicapiapp.R
import com.example.musicapiapp.adapter.MusicAdapter
import com.example.musicapiapp.adapter.MusicItemClick
import com.example.musicapiapp.databinding.FragmentPopBinding
import com.example.musicapiapp.domain.DomainMusic
import com.example.musicapiapp.model.MusicInfo
import com.example.musicapiapp.presenter.PopMusicPresenterContract
import com.example.musicapiapp.presenter.PopMusicViewContract
import javax.inject.Inject


class PopFragment : Fragment(), PopMusicViewContract {

    private val binding by lazy{
        FragmentPopBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var popPresenter:PopMusicPresenterContract

    private val musicAdapter by lazy{
        MusicAdapter(object : MusicItemClick {
            override fun onMusicClicked(music: DomainMusic) {
                val musicInfo = MusicInfo(music.artistName,music.trackName,music.albumCover,music.releaseDate,music.preview,music.trackPrice,
                    music.trackPrice,music.collectionName,music.country,music.trackNumber,music.trackCount)
                findNavController().navigate(R.id.action_navigation_Pop_Fragment_to_navigation_Details_Fragment, bundleOf(
                    Pair(DetailsFragment.MUSIC_DATA, musicInfo)
                )
                )
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.musicRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }

        MusicApp.component.inject(this)

        popPresenter.initializePresenter(this)
        popPresenter.getAllPopMusic()


        return binding.root
    }

    override fun loadingMusic(isLoading: Boolean) {
        Toast.makeText(requireContext(), "LOADING....",Toast.LENGTH_LONG).show()
    }

    override fun allPopMusicLoadedSuccess(music: List<DomainMusic>, isOffline: Boolean) {
        musicAdapter.updateMusic(music)
    }

    override fun error(error: Throwable) {
        Toast.makeText(requireContext(),"ERROR LOADING...", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        popPresenter.destroyPresenter()
    }


}