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
import com.example.musicapiapp.databinding.FragmentClassicalBinding
import com.example.musicapiapp.databinding.FragmentDetailsBinding
import com.example.musicapiapp.domain.DomainMusic
import com.example.musicapiapp.model.MusicInfo
import com.example.musicapiapp.presenter.ClassicalMusicPresenterContract
import com.example.musicapiapp.presenter.ClassicalMusicViewContract
import javax.inject.Inject

private const val TAG = "AllMusicFragment"
class ClassicalFragment : Fragment(), ClassicalMusicViewContract{

    private val binding by lazy{
        FragmentClassicalBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var classicalPresenter: ClassicalMusicPresenterContract //interface not implementation

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        MusicApp.component.inject(this)
//    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        MusicApp.component.inject(this)
//    }

    private val musicAdapter by lazy{
        MusicAdapter(object : MusicItemClick{
            override fun onMusicClicked(music: DomainMusic) {
                val musicInfo = MusicInfo(music.artistName,music.trackName,music.albumCover,music.releaseDate,music.preview,music.trackPrice,
                music.trackPrice,music.collectionName,music.country,music.trackNumber,music.trackCount)
                findNavController().navigate(R.id.action_navigation_Classical_Fragment_to_navigation_Details_Fragment, bundleOf(
                    Pair(DetailsFragment.MUSIC_DATA, musicInfo)
                )
                )
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.musicRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = musicAdapter
        }

        MusicApp.component.inject(this)

        classicalPresenter.initializePresenter(this)

        classicalPresenter.getAllClassicalMusic()



        return binding.root


    }

    override fun loadingMusic(isLoading: Boolean) {
        //maybe display loading spinner
        Toast.makeText(requireContext(), "LOADING....",Toast.LENGTH_LONG).show()
    }

//    override fun connectionChecked() {
//        TODO("Not yet implemented")
//    }

    override fun allMusicLoadedSuccess(music: List<DomainMusic>, isOffline: Boolean) {
        //set recycler view adapter here with new data
            musicAdapter.updateMusic(music)
        Toast.makeText(requireContext(), "LOADED SUCCESS",Toast.LENGTH_LONG).show()
    }

    override fun error(error: Throwable) {
        //display error to user
        Toast.makeText(requireContext(),"ERROR LOADING...",Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        classicalPresenter.destroyPresenter()
    }


}