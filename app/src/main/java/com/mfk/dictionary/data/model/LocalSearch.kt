package com.mfk.dictionary.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
@Entity
data class LocalSearch(
    @PrimaryKey(autoGenerate = true) var id: Int?=null,
    @ColumnInfo(name = "searchText") var searchText: String,
    @ColumnInfo(name = "createdAt") var createdAt: String
)
