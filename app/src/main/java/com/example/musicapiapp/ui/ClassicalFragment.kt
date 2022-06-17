package com.example.musicapiapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapiapp.MusicApp
import com.example.musicapiapp.R
import com.example.musicapiapp.adapter.MusicAdapter
import com.example.musicapiapp.adapter.MusicItemClick
import com.example.musicapiapp.databinding.FragmentClassicalBinding
import com.example.musicapiapp.domain.DomainMusic
import com.example.musicapiapp.model.Music
import com.example.musicapiapp.presenter.AllClassicalPresenter
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MusicApp.component.inject(this)
    }

    private val musicAdapter by lazy{
        MusicAdapter(object : MusicItemClick{
            override fun onMusicClicked(music: Music) {
                Log.d(TAG,"onMusicClick:${music.artistName}")
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