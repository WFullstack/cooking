package com.berakahnd.cookingreceipe.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berakahnd.cookingreceipe.data.model.CookingModel
import com.berakahnd.cookingreceipe.data.model.CookingModelItem
import com.berakahnd.cookingreceipe.data.repository.CookingRepository
import com.berakahnd.cookingreceipe.util.CookingResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CookingViewModel @Inject constructor(
    private val repository: CookingRepository
) : ViewModel(){
    val cookingUiState  = mutableStateOf(CookingUiState())
    var cookingModelItem = mutableStateOf(CookingModelItem())
    private var dataSearch : CookingModel = CookingModel()

    init {
        getCookingReceipe()
    }
    fun getCookingReceipe(){
        viewModelScope.launch {
           repository.getCookingReceipe().collect{ result ->
                when(result){
                    is CookingResult.Loading ->{
                        cookingUiState.value = cookingUiState.value.copy(
                            isLoading = true
                        )
                    }
                    is CookingResult.Error -> {
                        cookingUiState.value = cookingUiState.value.copy(isLoading = false,
                            error = result.message
                        )
                    }
                    is CookingResult.Success -> {
                        cookingUiState.value = cookingUiState.value.copy(isLoading = false,
                            data = result.data!!
                        )
                        dataSearch = result.data!!
                    }
                }
            }

        }

    }

    fun sendCooking(cookingItem : CookingModelItem){
        cookingModelItem.value = cookingItem
    }

    fun searchCookingFromName(search: String) {
        val results = mutableStateOf(CookingModel())
        dataSearch.forEachIndexed { index, cookingModelItem ->
            if (cookingModelItem.receipeName.contains(search, ignoreCase = true)) {
                results.value.add(cookingModelItem)
            }
        }
        cookingUiState.value = cookingUiState.value.copy(data = results.value)
    }
    fun findCookingByContinent(continent: String) {
        val results = mutableStateOf(CookingModel())
        dataSearch.forEachIndexed { index, cookingModelItem ->
            if (cookingModelItem.continent.equals(continent, ignoreCase = true)) {
                results.value.add(cookingModelItem)
            }
        }
        // reset technique
        if(continent.equals("All")){
            searchCookingFromName("a")
            return
        }
        cookingUiState.value = cookingUiState.value.copy(data = results.value)
    }
}