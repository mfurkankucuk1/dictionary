package com.mfk.dictionary.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
data class License(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)
