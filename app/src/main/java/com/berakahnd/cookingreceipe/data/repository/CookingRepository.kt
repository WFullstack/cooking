package com.berakahnd.cookingreceipe.data.repository

import com.berakahnd.cookingreceipe.data.remote.CookingApi
import com.berakahnd.cookingreceipe.util.CookingResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Constructor
import javax.inject.Inject

class CookingRepository  @Inject constructor(
    private val api: CookingApi
){
    suspend fun getCookingReceipe() = flow{

        emit(CookingResult.Loading)
        val data = api.getCookingRecipe().body()
        emit(CookingResult.Success(data = data!!))

    }.catch { error ->
        emit(CookingResult.Error(message = error.message.toString()))
    }
}