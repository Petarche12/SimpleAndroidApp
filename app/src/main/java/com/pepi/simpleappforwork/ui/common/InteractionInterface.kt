package com.pepi.simpleappforwork.ui.common

import com.pepi.simpleappforwork.data.model.Recipe

interface InteractionInterface {
    fun onItemSelected(position: Int, item: Recipe)
}