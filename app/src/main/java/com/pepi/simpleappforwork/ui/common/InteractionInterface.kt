package com.pepi.simpleappforwork.ui.common

import com.pepi.simpleappforwork.data.Recipe

interface InteractionInterface {
    fun onItemSelected(position: Int, item: Recipe)
}