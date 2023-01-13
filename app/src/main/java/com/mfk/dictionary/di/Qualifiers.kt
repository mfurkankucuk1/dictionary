package com.mfk.dictionary.di

import javax.inject.Qualifier

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DictionaryOkhttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DataMuseOkhttpClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DictionaryRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DataMuseRetrofit