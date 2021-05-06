package com.pepi.simpleappforwork.ui.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pepi.simpleappforwork.data.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AllViewModel @Inject constructor(
    repositoryImpl: RepositoryImpl,
) : ViewModel() {

    val recipes = repositoryImpl.getAllResults(viewModelScope).asLiveData(Dispatchers.IO)

}