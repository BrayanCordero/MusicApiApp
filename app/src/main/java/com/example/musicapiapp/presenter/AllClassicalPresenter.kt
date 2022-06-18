package com.example.musicapiapp.presenter

import com.example.musicapiapp.database.LocalDataRepository
import com.example.musicapiapp.domain.DomainMusic
import com.example.musicapiapp.domain.mapToDomainMusic
import com.example.musicapiapp.model.Music
import com.example.musicapiapp.rest.MusicRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class AllClassicalPresenter @Inject constructor(
    private val musicRepository: MusicRepository,
    private val compositeDisposable: CompositeDisposable,
    private val localMusicRepository: LocalDataRepository
): ClassicalMusicPresenterContract {


    private var musicViewContract: ClassicalMusicViewContract?=null

    //initializing presenter and passing reference for the view contract interface below
    override fun initializePresenter(viewContract: ClassicalMusicViewContract) {
        musicViewContract = viewContract
    }

//    register network state updates
    override fun registerForNetworkState() {
        TODO("NOT YET IMPLEMENTED")
//        musicRepository.checkNetworkAvailability()
    }

    override fun getAllClassicalMusic() {
        musicViewContract?.loadingMusic(true)
        getClassicalMusicFromNetwork()
    }

    //needed 100% to avoid memory leaks
    //this deallocates the view contract reference when the presenter needs to be destroyed
    //since onDestroy method will not destroy it.
    override fun destroyPresenter() {
        musicViewContract = null
        compositeDisposable.dispose()
        //compositeDisposable clears all observables when we don't need them anymore
    }



//if network is available get data from network
    private fun getClassicalMusicFromNetwork(){
        musicRepository.getAllClassicalMusic()
            .subscribeOn(Schedulers.io())  //moves to worker thread
            .flatMapCompletable {
                localMusicRepository?.insertMusic(it.music.mapToDomainMusic())
            }
            .observeOn(AndroidSchedulers.mainThread()) //gets data back to main thread
            .subscribe(
                {
                    getClassicalMusicFromDb()
                },
                { error->
                    musicViewContract?.error(error)
                    getClassicalMusicFromDb()
                }
            ).also { compositeDisposable.add(it) } // add it to the Disposable so that later we can clear it.
    }


    // this method is use to get data from RoomDB if network is offline
    private fun getClassicalMusicFromDb() {
        localMusicRepository.getAllClassicalMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {musicViewContract?.allMusicLoadedSuccess(it, true)},
                {musicViewContract?.error(it)}
            ).also { compositeDisposable.add(it) }
    }


}

interface ClassicalMusicViewContract{

    //extra method to do something while loading data from backend
    fun loadingMusic(isLoading:Boolean = false)


    //extra method while checking for connection
//    fun connectionChecked()

    //This will update the UI with the success response
    //implement in fragment or activity that will display this to user
    fun allMusicLoadedSuccess( music:List<DomainMusic>, isOffline:Boolean = false)

    //this will update if there is an error in the network call
    fun error(error:Throwable)


}


interface ClassicalMusicPresenterContract{
    fun initializePresenter(viewContract: ClassicalMusicViewContract)
    fun registerForNetworkState()
    fun getAllClassicalMusic()
    fun destroyPresenter()
}