package com.mfk.dictionary.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
data class DataMuse(
    @SerializedName("word") var word: String? = null,
    @SerializedName("score") var score: Int? = null
)
