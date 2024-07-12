package com.berakahnd.cookingreceipe.util

sealed class CookingResult<out T> {
    data class Success<out T>(val data : T) : CookingResult<T>()
    data class Error(val message : String) : CookingResult<Nothing>()
    object Loading : CookingResult<Nothing>()
}