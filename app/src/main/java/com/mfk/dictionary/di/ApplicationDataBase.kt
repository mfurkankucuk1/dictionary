package com.mfk.dictionary.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mfk.dictionary.data.model.LocalSearch
import com.mfk.dictionary.data.remote.DatabaseDao

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
@Database(entities = [LocalSearch::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun getDao(): DatabaseDao
}