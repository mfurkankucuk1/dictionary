package com.mfk.dictionary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
@HiltAndroidApp
class DictionaryApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

}