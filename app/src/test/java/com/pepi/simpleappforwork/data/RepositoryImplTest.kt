package com.pepi.simpleappforwork.data

import com.google.common.truth.Truth.assertThat
import com.pepi.simpleappforwork.api.RecipeApi
import com.pepi.simpleappforwork.common.util.Resource
import com.pepi.simpleappforwork.data.database.RecipeDao
import com.pepi.simpleappforwork.data.model.Recipe
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception

@RunWith(JUnit4::class)
class RepositoryImplTest {

    private val recipeDao = mockk<RecipeDao>(relaxed = true)
    private val recipeApi = mockk<RecipeApi>(relaxed = true)
    private val repository: RepositoryImpl = RepositoryImpl(recipeApi, recipeDao)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `Gets all the results for recipes`() {

    }

    @Test
    fun `Gets all the favourite recipes when no exception`() = runBlocking {
        //given
        val list = mockk<List<Recipe>>()
        //when
        every { recipeDao.getRecipes() } returns flow {
            emit(list)
        }
        //than
        repository.getFavouriteRecipes().collectIndexed { index, value ->
            when (index) {
                0 -> {
                    assertThat(value).isInstanceOf(Resource.Loading::class.java)
                }
                1 -> {
                    assertThat(value).isInstanceOf(Resource.Success::class.java)
                    assertThat(value.data).isEqualTo(list)
                }
            }
        }
    }

    @Test
    fun `Gets all the favourite recipes when there is exception`() = runBlocking {
        //given
        val list = mockk<List<Recipe>>()
        //when
        every { recipeDao.getRecipes() } throws Exception("test")
        //than
        repository.getFavouriteRecipes().collectIndexed { index, value ->
            when (index) {
                0 -> {
                    assertThat(value).isInstanceOf(Resource.Loading::class.java)
                }
                1 -> {
                    assertThat(value).isInstanceOf(Resource.Error::class.java)
                }
            }
        }
    }

    @Test
    fun `Inserts recipe in local database`() = runBlocking {
        //given
        val recipe = mockk<Recipe>()
        //when
        repository.insertRecipe(recipe)
        //than
        coVerify(exactly = 1) { recipeDao.insertRecipe(recipe) }
    }

    @Test
    fun `Removes recipe from local database`() = runBlocking {
        //given
        val recipe = mockk<Recipe>()
        //when
        repository.deleteRecipe(recipe)
        //than
        coVerify(exactly = 1) { recipeDao.deleteRecipe(recipe) }
    }
}