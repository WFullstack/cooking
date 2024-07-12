package com.berakahnd.cookingreceipe.di

import android.content.Context
import androidx.room.Room
import com.berakahnd.cookingreceipe.data.local.FavCookingReceipeDB
import com.berakahnd.cookingreceipe.data.local.FavCookingReceipeDao
import com.berakahnd.cookingreceipe.data.remote.CookingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    private const val url : String = "https://drive.google.com"
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit : Retrofit) : CookingApi{
        return  retrofit.create(CookingApi::class.java)
    }

    // ROOM
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context) : FavCookingReceipeDB{
        return Room.databaseBuilder(
            context,
            FavCookingReceipeDB::class.java,
            "FavCookingReceipeDao.db"
        ).build()
    }

    @Provides
    fun provideDao(db : FavCookingReceipeDB) : FavCookingReceipeDao{
        return db.getCookingReceipeDao()
    }
}