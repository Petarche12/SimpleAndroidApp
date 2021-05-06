package com.pepi.simpleappforwork.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pepi.simpleappforwork.api.RecipeApi
import com.pepi.simpleappforwork.data.model.Recipe
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_OFFSET_INDEX = 0
private const val TAG = "RecipePagingSource"

class RecipePagingSource(
    private val api: RecipeApi,
    private val query: String
) : PagingSource<Int, Recipe>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val offset = params.key ?: UNSPLASH_STARTING_OFFSET_INDEX
        return try {
            val response = api.searchRecipes(offset = offset, number = params.loadSize)
            val recipes = response.results
            Log.d(TAG, "load: ${response.results.size}")
            LoadResult.Page(
                data = recipes,
                prevKey = if (offset < UNSPLASH_STARTING_OFFSET_INDEX + 10) null else offset - 10,
                nextKey = if (recipes.isEmpty()) null else offset + 10
            )
        } catch (e: IOException) //ako nema internet
        {
            LoadResult.Error(e)
        } catch (e: HttpException) //ako nesto ne e dobro so requestot, ako ne sme authorised na pr ili nema data
        {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(20) ?: anchorPage?.nextKey?.minus(20)
        }
    }

}