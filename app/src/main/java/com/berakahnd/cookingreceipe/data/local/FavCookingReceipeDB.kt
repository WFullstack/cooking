package com.berakahnd.cookingreceipe.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FavCookingReceipe::class], version = 1)
@TypeConverters(Converters::class)
 abstract  class FavCookingReceipeDB : RoomDatabase(){
    abstract fun getCookingReceipeDao() : FavCookingReceipeDao
}