package com.berakahnd.cookingreceipe.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berakahnd.cookingreceipe.data.local.FavCookingReceipe
import com.berakahnd.cookingreceipe.data.repository.FavRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val repository: FavRepository
) : ViewModel(){
    val favUiState  = mutableStateOf(FavUiState())

    init {
        getFavCookingReceipe()
    }

    private fun getFavCookingReceipe(){
        favUiState.value = favUiState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                repository.getAllFavorite().collect{ result ->
                    favUiState.value = favUiState.value.copy(isLoading = false,data = result)
                    //Log.i("FAV",result.toString())
                }
            }catch (e : Exception){
                //Log.i("FAV",e.message.toString())
                favUiState.value = favUiState.value.copy(isLoading = false,error = e.message.toString())
            }
        }
    }
    fun insertFav(favCookingReceipe: FavCookingReceipe){
        viewModelScope.launch {
            try{
                repository.insertFav(favCookingReceipe)
            }catch (e : Exception){
                Log.i("FAV",e.message.toString())
            }
        }
    }
    fun updateFav(favCookingReceipe: FavCookingReceipe){
        viewModelScope.launch {
            repository.updateFav(favCookingReceipe)
        }
    }
    fun deleteFav(favCookingReceipe: FavCookingReceipe){
        viewModelScope.launch {
            repository.deleteFav(favCookingReceipe)
        }
    }
}