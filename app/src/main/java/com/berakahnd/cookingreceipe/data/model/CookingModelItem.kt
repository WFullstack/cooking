package com.berakahnd.cookingreceipe.data.model

data class CookingModelItem(
    val continent: String ="",
    val cookingSteps: List<String> = emptyList(),
    val cookingTime: String ="",
    val imageUrl: String ="",
    val ingredients: List<String> = emptyList(),
    val receipeName: String =""
)