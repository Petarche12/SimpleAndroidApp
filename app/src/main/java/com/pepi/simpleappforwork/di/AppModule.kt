package com.pepi.simpleappforwork.di

import android.app.Application
import androidx.room.Room
import com.pepi.simpleappforwork.api.RecipeApi
import com.pepi.simpleappforwork.data.database.RecipeDao
import com.pepi.simpleappforwork.data.database.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(RecipeApi.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRecipeApi(retrofit: Retrofit): RecipeApi =
        retrofit.create(RecipeApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): RecipeDatabase =
        Room.databaseBuilder(app, RecipeDatabase::class.java, "recipe_database")
            .build()

    @Provides
    @Singleton
    fun provideRecipeDatabaseDao(recipeDatabase: RecipeDatabase): RecipeDao =
        recipeDatabase.recipeDao()

}