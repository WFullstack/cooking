package com.berakahnd.cookingreceipe.data.remote

import com.berakahnd.cookingreceipe.data.model.CookingModel
import retrofit2.Response
import retrofit2.http.GET

interface CookingApi {
    @GET("/uc?export=download&id=17IQbkPQqjMI-nYuOuDH1Pa-0UTgApzwB")
    suspend fun getCookingRecipe(): Response<CookingModel>
}