package com.mfk.dictionary.data.remote

import com.mfk.dictionary.data.model.DataMuse
import com.mfk.dictionary.utils.Constants
import com.mfk.dictionary.utils.EndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
interface DatamuseService {


    @GET(EndPoints.DATA_MUSE_END_POINT)
    suspend fun getDataMuse(@Query(Constants.DATA_MUSE_QUERY) word: String): Response<List<DataMuse>>


}