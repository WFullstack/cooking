package com.berakahnd.cookingreceipe.ui.viewmodel

import com.berakahnd.cookingreceipe.data.local.FavCookingReceipe

data class FavUiState(
    val isLoading : Boolean = false,
    val data : List<FavCookingReceipe> = emptyList(),
    val error : String = ""
)
