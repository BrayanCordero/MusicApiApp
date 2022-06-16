package com.example.musicapiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicapiapp.R
import com.example.musicapiapp.domain.DomainMusic
import com.example.musicapiapp.presenter.AllClassicalPresenter
import com.example.musicapiapp.presenter.ClassicalMusicPresenterContract
import com.example.musicapiapp.presenter.ClassicalMusicViewContract
import javax.inject.Inject


class ClassicalFragment : Fragment(), ClassicalMusicViewContract{

    @Inject
    lateinit var classicalPresenter: ClassicalMusicPresenterContract //interface not implementation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        classicalPresenter.initializePresenter(this)
        return inflater.inflate(R.layout.fragment_classical, container, false)
    }

    override fun loadingMusic(isLoading: Boolean) {
        //maybe display loading spinner
        TODO("Not yet implemented")
    }

//    override fun connectionChecked() {
//        TODO("Not yet implemented")
//    }

    override fun allMusicLoadedSuccess(music: List<DomainMusic>, isOffline: Boolean) {
        //set recycler view adapter here with new data
        TODO("Not yet implemented")
    }

    override fun error(error: Throwable) {
        //display error to user
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        classicalPresenter.destroyPresenter()
    }

}