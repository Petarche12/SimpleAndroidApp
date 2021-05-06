package com.pepi.simpleappforwork.data

import androidx.paging.PagingData
import com.pepi.simpleappforwork.common.util.Resource
import com.pepi.simpleappforwork.data.model.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface Repository {
    //DOCUMENTATION NEEDED
    fun getAllResults(scope: CoroutineScope) : Flow<Resource<out PagingData<Recipe>>>
    fun getFavouriteRecipes() : Flow<Resource<out List<Recipe>>>
    suspend fun insertRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
}