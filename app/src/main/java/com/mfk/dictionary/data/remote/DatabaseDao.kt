package com.mfk.dictionary.data.remote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mfk.dictionary.data.model.LocalSearch

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search: LocalSearch): Long

    @Query("SELECT * FROM LocalSearch ORDER BY createdAt DESC LIMIT 5")
    suspend fun getLastSearch(): List<LocalSearch>
}