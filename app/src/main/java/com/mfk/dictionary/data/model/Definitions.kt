package com.mfk.dictionary.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
data class Definitions(
    @SerializedName("definition") var definition: String? = null,
    @SerializedName("synonyms") var synonyms: ArrayList<String> = arrayListOf(),
    @SerializedName("antonyms") var antonyms: ArrayList<String> = arrayListOf(),
    @SerializedName("example") var example: String? = null

)
