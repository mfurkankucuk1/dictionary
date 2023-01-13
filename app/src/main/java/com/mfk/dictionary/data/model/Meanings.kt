package com.mfk.dictionary.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
data class Meanings(
    @SerializedName("partOfSpeech") var partOfSpeech: String? = null,
    @SerializedName("definitions") var definitions: ArrayList<Definitions> = arrayListOf(),
    @SerializedName("synonyms") var synonyms: ArrayList<String> = arrayListOf(),
    @SerializedName("antonyms") var antonyms: ArrayList<String> = arrayListOf()
)
