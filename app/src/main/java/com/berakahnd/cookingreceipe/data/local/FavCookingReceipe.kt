package com.berakahnd.cookingreceipe.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavCookingReceipe(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val receipeName: String ="",
    val continent: String ="",
    val cookingSteps: List<String> = emptyList(),
    val cookingTime: String ="",
    val imageUrl: String ="",
    val ingredients: List<String> = emptyList()
)
