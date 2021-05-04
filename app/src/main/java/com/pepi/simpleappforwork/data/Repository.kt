package com.pepi.simpleappforwork.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.pepi.simpleappforwork.api.RecipeApi
import com.pepi.simpleappforwork.common.util.networkBoundResource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository
@Inject
constructor(private val unsplashApi: RecipeApi) {
    private fun getSearch(query: String = "Cake",scope: CoroutineScope) =
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
            getSearch(scope = scope)
        },
        fetch = {
            getSearch(scope = scope)
        },
        saveFetchResult = {
        }
    )
}

