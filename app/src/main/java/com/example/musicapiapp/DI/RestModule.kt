package com.example.musicapiapp.DI

import com.example.musicapiapp.rest.MusicServiceApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RestModule {

    @Provides
    fun providesGson(): Gson =Gson()

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun providesRetrofit(okHttpsClient:OkHttpClient, gson:Gson): Retrofit = Retrofit.Builder()
        .baseUrl(MusicServiceApi.BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpsClient)
        .build()

    @Provides
    fun providesMusicService(retrofit: Retrofit): MusicServiceApi =
        retrofit.create(MusicServiceApi::class.java)

    @Provides
    fun providesOkHttpsClient(LoggingIntercepter : HttpLoggingInterceptor) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(LoggingIntercepter)
        .connectTimeout(30,TimeUnit.SECONDS)
        .readTimeout(30,TimeUnit.SECONDS)
        .writeTimeout(30,TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideCompositeDisposable():CompositeDisposable =
        CompositeDisposable()
}