package com.pepi.simpleappforwork.api

import com.pepi.simpleappforwork.data.Recipe

data class RecipeResponse(
    val number: Int,
    val offset: Int,
    val results: List<Recipe>,
    val totalResults: Int
)