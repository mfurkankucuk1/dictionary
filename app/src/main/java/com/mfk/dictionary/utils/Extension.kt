package com.mfk.dictionary.utils

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.util.*

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/

fun getCurrentTime(): String {
    val currentTime: Date = Calendar.getInstance().time
    return currentTime.toString()
}

fun View.remove() {
    this.visibility = View.GONE
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun Fragment.customNavigate(navigateId:Int,bundle:Bundle?){
    bundle?.let {
        this.findNavController().navigate(navigateId,bundle)
    } ?: run {
        this.findNavController().navigate(navigateId)
    }
}
