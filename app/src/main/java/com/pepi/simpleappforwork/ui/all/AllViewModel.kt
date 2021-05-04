package com.pepi.simpleappforwork.ui.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pepi.simpleappforwork.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AllViewModel @Inject constructor(
    repository: Repository,
) : ViewModel() {

    val recipes = repository.getAllResults(viewModelScope).asLiveData(Dispatchers.IO)

}