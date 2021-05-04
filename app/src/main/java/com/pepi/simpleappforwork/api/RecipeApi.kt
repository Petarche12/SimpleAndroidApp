package com.pepi.simpleappforwork.api

import retrofit2.http.GET

import retrofit2.http.Query

interface RecipeApi {

    companion object {
        const val BASE_URL = "https://api.spoonacular.com/"
    }

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String = "Cake",
        @Query("offset") offset: Int,
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String = "b538b39f29374fddba2fc69efc0b2364"
    ): RecipeResponse

}