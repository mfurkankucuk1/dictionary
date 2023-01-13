package com.mfk.dictionary.utils

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T : Any>(data: T?) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null):Resource<T>(data,message)
    class Loading<T>:Resource<T>()
}