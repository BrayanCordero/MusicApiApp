package com.example.musicapiapp.DI

import com.example.musicapiapp.rest.MusicServiceApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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
}