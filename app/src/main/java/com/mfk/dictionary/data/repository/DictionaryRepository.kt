package com.mfk.dictionary.data.repository

import com.mfk.dictionary.data.model.LocalSearch
import com.mfk.dictionary.data.remote.DatabaseDao
import com.mfk.dictionary.data.remote.DatamuseService
import com.mfk.dictionary.data.remote.DictionaryService
import javax.inject.Inject

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
class DictionaryRepository @Inject constructor(
    private val databaseDao: DatabaseDao,
    private val dictionaryService: DictionaryService
) {

    suspend fun insertSearch(localSearch: LocalSearch) = databaseDao.insert(localSearch)

    suspend fun getLastSearch() = databaseDao.getLastSearch()

    suspend fun getWordDetail(word:String) = dictionaryService.getSearchDetail(word)


}