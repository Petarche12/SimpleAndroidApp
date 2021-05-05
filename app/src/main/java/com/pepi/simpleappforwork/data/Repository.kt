package com.pepi.simpleappforwork.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.pepi.simpleappforwork.api.RecipeApi
import com.pepi.simpleappforwork.common.util.Resource
import com.pepi.simpleappforwork.common.util.networkBoundResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository
@Inject
constructor(private val unsplashApi: RecipeApi, private val recipeDao: RecipeDao) {

    private fun getSearch(query: String = "Cake", scope: CoroutineScope) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100, //koga da pocnat da se drop rezultatite
                enablePlaceholders = false //da ne dava placeholders samata
            ),
            pagingSourceFactory = { RecipePagingSource(unsplashApi, query) }
        ).flow.cachedIn(scope)

    fun getAllResults(scope: CoroutineScope) = networkBoundResource(
        query = {
            getSearch(scope = scope)  //should be the cashed data not the same as in fetch
        },
        fetch = {
            getSearch(scope = scope)
        },
        saveFetchResult = {
        }
    )

    fun getFavouriteRecipes() = flow {
        emit(Resource.Loading(null))
        try {
            recipeDao.getRecipes().collect{
                emit(Resource.Success(it))
            }
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable, null))
        }
    }


    suspend fun insertRecipe(recipe: Recipe) = recipeDao.insertRecipe(recipe)
    suspend fun deleteRecipe(recipe: Recipe) = recipeDao.deleteRecipe(recipe)
}

