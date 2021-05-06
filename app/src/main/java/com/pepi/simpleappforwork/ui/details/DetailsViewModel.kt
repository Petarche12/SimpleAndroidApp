package com.pepi.simpleappforwork.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepi.simpleappforwork.data.model.Recipe
import com.pepi.simpleappforwork.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {

    fun insertRecipe(recipe: Recipe) = viewModelScope.launch(Dispatchers.IO) {
        repositoryImpl.insertRecipe(recipe)
    }

    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch(Dispatchers.IO) {
        repositoryImpl.deleteRecipe(recipe)
    }

}