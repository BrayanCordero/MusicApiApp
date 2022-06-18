package com.example.musicapiapp.presenter


import com.example.musicapiapp.database.LocalDataRepository
import com.example.musicapiapp.domain.DomainMusic
import com.example.musicapiapp.domain.mapToDomainMusic
import com.example.musicapiapp.rest.MusicRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AllRockPresenter @Inject constructor(
    private val musicRepository: MusicRepository,
    private val compositeDisposable: CompositeDisposable,
    private val localMusicRepository: LocalDataRepository
): RockMusicPresenterContract{


    private var musicViewContract:RockMusicViewContract?=null

    override fun initializePresenter(viewContract: RockMusicViewContract) {
        musicViewContract = viewContract
    }

    override fun registerForNetworkState() {
        TODO("Not yet implemented")
    }

    override fun getAllRockMusic() {
        musicViewContract?.loadingMusic(true)
        getRockMusicFromNetwork()

    }

    override fun destroyPresenter() {
        musicViewContract =null
        compositeDisposable.dispose()
    }


    private fun getRockMusicFromNetwork(){
    musicRepository.getAllRockMusic()
        .subscribeOn((Schedulers.io()))
        .flatMapCompletable {
            localMusicRepository?.insertMusic(it.music.mapToDomainMusic())
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
                getAllRockMusicFromDb()
            },
            {
                error->
                musicViewContract?.error(error)
                getAllRockMusicFromDb()
            }
        ).also { compositeDisposable.add(it) }
    }

    private fun getAllRockMusicFromDb() {
        localMusicRepository.getAllRockMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {musicViewContract?.allMusicLoadedSuccess(it,true)},
                {musicViewContract?.error(it)}
            ).also { compositeDisposable.add(it) }
    }
}


interface RockMusicViewContract{
    fun loadingMusic(isLoading:Boolean=false)

    fun allMusicLoadedSuccess(music:List<DomainMusic>,isOffline:Boolean=false)

    fun error(error:Throwable)
}

interface RockMusicPresenterContract{
    fun initializePresenter(viewContract: RockMusicViewContract)
    fun registerForNetworkState()
    fun getAllRockMusic()
    fun destroyPresenter()
}