package com.example.musicapiapp.presenter

import com.example.musicapiapp.database.LocalDataRepository
import com.example.musicapiapp.domain.DomainMusic
import com.example.musicapiapp.domain.mapToDomainMusic
import com.example.musicapiapp.rest.MusicRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AllPopPresenter @Inject constructor(
    private val musicRepository:MusicRepository,
    private val compositeDisposable: CompositeDisposable,
    private val localMusicRepository: LocalDataRepository
):PopMusicPresenterContract{

    private var musicViewContract: PopMusicViewContract?=null

    override fun initializePresenter(viewContract: PopMusicViewContract) {
        musicViewContract= viewContract
    }

    override fun registerForNetworkState() {
        TODO("Not yet implemented")
    }

    override fun getAllPopMusic() {
        musicViewContract?.loadingMusic(true)
        getPopMusicFromNetwork()
    }

    override fun destroyPresenter() {
        musicViewContract = null
        compositeDisposable.dispose()
    }

    private fun getPopMusicFromNetwork(){
        musicRepository.getAllPopMusic()
            .subscribeOn(Schedulers.io())
            .flatMapCompletable{
                localMusicRepository.insertMusic(it.music.mapToDomainMusic())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                getPopMusicFromDb()
                },
                {
                    error ->
                    musicViewContract?.error(error)
                    getPopMusicFromDb()
                }
            ).also { compositeDisposable.add(it) }
    }

    private fun getPopMusicFromDb(){
        localMusicRepository.getAllPopMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {musicViewContract?.allPopMusicLoadedSuccess(it,true)},
                {musicViewContract?.error(it)}
            ).also { compositeDisposable.add(it) }
    }


}

interface PopMusicViewContract{
    fun loadingMusic(isLoading:Boolean=false)
    fun allPopMusicLoadedSuccess(music:List<DomainMusic>, isOffline:Boolean=false)
    fun error(error:Throwable)
}

interface PopMusicPresenterContract{
    fun initializePresenter(viewContract: PopMusicViewContract)
    fun registerForNetworkState()
    fun getAllPopMusic()
    fun destroyPresenter()
}