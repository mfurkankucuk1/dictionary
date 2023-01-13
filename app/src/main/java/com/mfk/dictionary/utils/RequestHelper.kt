package com.mfk.dictionary.utils

import com.mfk.dictionary.utils.Constants.ERROR
import com.mfk.dictionary.utils.Constants.ERROR_401
import com.mfk.dictionary.utils.Constants.ERROR_500
import retrofit2.Response

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
object RequestHelper {

    fun <T> handleResponse(response: Response<T>): Resource<T> {
        if (response != null) {
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    return Resource.Success(result) as Resource<T>
                }
            } else {
                val responseCode = response.code()

                if (responseCode == 401) {
                    return Resource.Error(ERROR_401)
                } else if (responseCode in 500..599) {
                    return Resource.Error(ERROR_500)
                }
            }
        }
        return Resource.Error(ERROR)
    }


}