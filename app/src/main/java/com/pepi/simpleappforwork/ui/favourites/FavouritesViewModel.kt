package com.pepi.simpleappforwork.ui.favourites


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pepi.simpleappforwork.data.Recipe
import com.pepi.simpleappforwork.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val favouriteRecipes = repository.getFavouriteRecipes().asLiveData(Dispatchers.IO)

}