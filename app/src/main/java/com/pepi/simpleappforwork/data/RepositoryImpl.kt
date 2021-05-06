package com.pepi.simpleappforwork.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.pepi.simpleappforwork.api.RecipeApi
import com.pepi.simpleappforwork.common.util.Resource
import com.pepi.simpleappforwork.common.util.networkBoundResource
import com.pepi.simpleappforwork.data.database.RecipeDao
import com.pepi.simpleappforwork.data.model.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl
@Inject
constructor(private val recipeApi: RecipeApi, private val recipeDao: RecipeDao) : Repository {

    private fun getSearch(query: String = "Cake", scope: CoroutineScope) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100, //koga da pocnat da se drop rezultatite
                enablePlaceholders = false //da ne dava placeholders samata
            ),
            pagingSourceFactory = { RecipePagingSource(recipeApi, query) }
        ).flow.cachedIn(scope)

    override fun getAllResults(scope: CoroutineScope) = networkBoundResource(
        query = {
            getSearch(scope = scope)  //should be the cashed data not the same as in fetch
        },
        fetch = {
            getSearch(scope = scope)
        },
        saveFetchResult = {}
    )

    override fun getFavouriteRecipes() = flow {
        emit(Resource.Loading(null))
        try {
            recipeDao.getRecipes().collect {
                emit(Resource.Success(it))
            }
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable, null))
        }
    }

    override suspend fun insertRecipe(recipe: Recipe) = recipeDao.insertRecipe(recipe)
    override suspend fun deleteRecipe(recipe: Recipe) = recipeDao.deleteRecipe(recipe)
}

