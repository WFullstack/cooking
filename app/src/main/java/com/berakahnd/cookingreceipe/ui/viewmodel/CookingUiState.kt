package com.berakahnd.cookingreceipe.ui.viewmodel

import com.berakahnd.cookingreceipe.data.model.CookingModel

data class CookingUiState(
    val isLoading : Boolean = false,
    var data : CookingModel = CookingModel(),
    val error : String = ""
)
