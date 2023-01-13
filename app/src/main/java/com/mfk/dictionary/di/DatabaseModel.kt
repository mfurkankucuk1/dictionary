package com.mfk.dictionary.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.mfk.dictionary.data.remote.DatabaseDao
import com.mfk.dictionary.data.repository.PreferencesRepository
import com.mfk.dictionary.utils.Constants
import com.mfk.dictionary.utils.Constants.PRODUCT_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModel {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ApplicationDatabase {

        val dbBuilder = Room.databaseBuilder(
            context, ApplicationDatabase::class.java, PRODUCT_DATABASE_NAME
        )
        return dbBuilder.allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideProductDao(appDatabase: ApplicationDatabase): DatabaseDao {
        return appDatabase.getDao()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providePreferencesHelper(sharedPreferences: SharedPreferences): PreferencesRepository =
        PreferencesRepository(sharedPreferences)
}