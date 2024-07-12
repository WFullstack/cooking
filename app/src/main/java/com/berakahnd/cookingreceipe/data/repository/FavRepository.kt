package com.berakahnd.cookingreceipe.data.repository

import com.berakahnd.cookingreceipe.data.local.FavCookingReceipe
import com.berakahnd.cookingreceipe.data.local.FavCookingReceipeDao
import javax.inject.Inject

class FavRepository @Inject constructor(
    val dao : FavCookingReceipeDao
) {
    suspend fun insertFav(favCookingReceipe: FavCookingReceipe) = dao.insert(favCookingReceipe)
    suspend fun updateFav(favCookingReceipe: FavCookingReceipe) = dao.update(favCookingReceipe)
    suspend fun deleteFav(favCookingReceipe: FavCookingReceipe) = dao.delete(favCookingReceipe)
    fun getAllFavorite() = dao.getAllFavorite()
}