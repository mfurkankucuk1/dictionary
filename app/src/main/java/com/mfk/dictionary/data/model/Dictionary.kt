package com.mfk.dictionary.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
data class Dictionary(
    @SerializedName("word") var word: String? = null,
    @SerializedName("phonetic") var phonetic: String? = null,
    @SerializedName("phonetics") var phonetics: ArrayList<Phonetics> = arrayListOf(),
    @SerializedName("meanings") var meanings: ArrayList<Meanings> = arrayListOf(),
    @SerializedName("license") var license: License? = License(),
    @SerializedName("sourceUrls") var sourceUrls: ArrayList<String> = arrayListOf()

)
