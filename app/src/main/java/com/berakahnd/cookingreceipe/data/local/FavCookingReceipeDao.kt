package com.berakahnd.cookingreceipe.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.berakahnd.cookingreceipe.data.model.CookingModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavCookingReceipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cookingReceipe: FavCookingReceipe)
    
    @Update
    suspend fun update(cookingReceipe: FavCookingReceipe)
    
    @Delete
    suspend fun delete(cookingReceipe: FavCookingReceipe)
    
    @Query("SELECT * FROM FavCookingReceipe")
    fun getAllFavorite() : Flow<List<FavCookingReceipe>>
}