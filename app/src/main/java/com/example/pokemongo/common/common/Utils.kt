package com.example.pokemongo.common.common

object Utils {
    fun getIndex(url: String) = url.split("/".toRegex()).dropLast(1).last()

    fun getSrcUrl(url: String) = "https://pokeres.bastionbot.org/images/pokemon/"+ getIndex(url) +".png"
}