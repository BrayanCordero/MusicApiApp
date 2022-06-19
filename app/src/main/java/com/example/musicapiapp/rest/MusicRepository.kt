package com.example.musicapiapp.rest

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.example.musicapiapp.model.MusicResponse
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

interface MusicRepository {
//    val networkState:BehaviorSubject<Boolean>
    fun getAllClassicalMusic(): Single<MusicResponse>
//    fun checkNetworkAvailability()
    fun getAllPopMusic():Single<MusicResponse>

    fun getAllRockMusic():Single<MusicResponse>
}

class MusicRepositoryImpl @Inject constructor(
    private val serviceApi: MusicServiceApi
//    private val networkRequest: NetworkRequest,
//    private val connectivityManager: ConnectivityManager
): MusicRepository{

    override fun getAllClassicalMusic(): Single<MusicResponse> {
        return serviceApi.getAllClassicalMusic()
    }

    override fun getAllPopMusic(): Single<MusicResponse> {
        return serviceApi.getAllPopMusic()
    }

    override fun getAllRockMusic(): Single<MusicResponse> {
        return serviceApi.getAllRockMusic()
    }

//    override val networkState: BehaviorSubject<Boolean> = BehaviorSubject.create()


//    override fun checkNetworkAvailability() {
//        connectivityManager.requestNetwork(networkRequest,object :
//        ConnectivityManager.NetworkCallback(){
//            override fun onAvailable(network: Network) {
//                super.onAvailable(network)
//                networkState.onNext(true)
//            }
//
//            override fun onUnavailable() {
//                super.onUnavailable()
//                networkState.onNext(false)
//            }
//
//            override fun onLost(network: Network) {
//                super.onLost(network)
//                networkState.onNext(false)
//            }
//        })
//    }


//    private fun checkCapabilities():NetworkCapabilities?{
//        connectivityManager?.activeNetwork?.let{
//            return connectivityManager.getNetworkCapabilities(it)
//        } ?: return null
//    }


//    fun checkNetwork(): Boolean {
//        return checkCapabilities()?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false && checkCapabilities()?.hasCapability(
//            NetworkCapabilities.NET_CAPABILITY_VALIDATED
//        ) ?: false
//    }

}