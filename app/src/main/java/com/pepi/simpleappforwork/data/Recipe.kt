package com.pepi.simpleappforwork.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
) : Parcelable