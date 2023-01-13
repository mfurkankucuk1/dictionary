package com.mfk.dictionary.utils

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
object Constants {

    const val BASE_URL = "https://api.dictionaryapi.dev/"
    const val DATA_MUSE_BASE_URL = "https://api.datamuse.com/"

    /**
     * Query
     * **/

    const val DATA_MUSE_QUERY = "rel_syn"

    /**
     * Databse
     * **/

    const val PRODUCT_DATABASE_NAME = "searchDb"

    /**
     * Preferences
     * **/
    const val PREFERENCES_NAME = "prefName"


    /**
     * Bundle Key
     * **/

    const val SEARCH_TEXT_BUNDLE_KEY = "searchTextBundleKey"

    /**
     * Errors
     * **/
    const val ERROR_401 = "Unauthorized operation"
    const val ERROR_500 = "Something is wrong with the server"
    const val ERROR = "An unexpected problem occurred, please try again later"
    const val NO_INTERNET_CONNECTION = "Please check your internet connection"
    const val NOT_FOUND = "Not Found"

}