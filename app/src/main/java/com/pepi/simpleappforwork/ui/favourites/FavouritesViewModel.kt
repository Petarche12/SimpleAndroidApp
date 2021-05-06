package com.pepi.simpleappforwork.ui.favourites


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pepi.simpleappforwork.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {

    val favouriteRecipes = repositoryImpl.getFavouriteRecipes().asLiveData(Dispatchers.IO)

}