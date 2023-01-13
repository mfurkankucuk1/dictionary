package com.mfk.dictionary.data.repository

import com.mfk.dictionary.data.remote.DatamuseService
import javax.inject.Inject

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
class DataMuseRepository @Inject constructor(private val dataMuseService: DatamuseService){

    suspend fun getDataMuse(word:String) = dataMuseService.getDataMuse(word)


}