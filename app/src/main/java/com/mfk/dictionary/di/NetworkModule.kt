package com.mfk.dictionary.di

import com.mfk.dictionary.data.remote.DatamuseService
import com.mfk.dictionary.data.remote.DictionaryService
import com.mfk.dictionary.utils.Constants.BASE_URL
import com.mfk.dictionary.utils.Constants.DATA_MUSE_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @DataMuseOkhttpClient
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @DictionaryOkhttpClient
    fun provideDictionaryHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @DictionaryRetrofit
    fun provideRetrofit(
        @DictionaryOkhttpClient okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @DataMuseRetrofit
    fun provideDatamuseRetrofit(
        @DataMuseOkhttpClient okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DATA_MUSE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }


    @Provides
    fun provideService(@DictionaryRetrofit retrofit: Retrofit): DictionaryService {
        return retrofit.create(DictionaryService::class.java)
    }

    @Provides
    fun provideDatamuseService(@DataMuseRetrofit retrofit: Retrofit): DatamuseService {
        return retrofit.create(DatamuseService::class.java)
    }


}