package com.busealkan.covid19.util

import com.busealkan.covid19.Constants
import java.text.SimpleDateFormat
import java.util.*

fun String?.toLowerCaseTr():String{
    if(!this.isNullOrEmpty()){
        return this.toLowerCase(Locale(Constants.LOCALE_TR))
    }
    else{
        return ""

    }
}

fun String.formatDateTimeCovid():String{
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val parsedDate = sdf.parse(this)
    val systemSdp = SimpleDateFormat("d MMMM yyyy HH:mm", Locale.getDefault())
    return  systemSdp.format(parsedDate)


}