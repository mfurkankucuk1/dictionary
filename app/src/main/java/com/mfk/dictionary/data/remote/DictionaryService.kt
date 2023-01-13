package com.mfk.dictionary.data.remote

import com.mfk.dictionary.data.model.Dictionary
import com.mfk.dictionary.utils.Constants
import com.mfk.dictionary.utils.EndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
interface DictionaryService {

    @GET("api/v2/entries/en/{word}")
    suspend fun getSearchDetail(@Path("word") word:String):Response<List<Dictionary>>


}