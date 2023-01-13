package com.mfk.dictionary.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
data class Phonetics(
    @SerializedName("audio") var audio: String? = null,
    @SerializedName("sourceUrl") var sourceUrl: String? = null,
    @SerializedName("license") var license: License? = License()
)