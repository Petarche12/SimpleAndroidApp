package com.pepi.simpleappforwork.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val imageType: String,
    val title: String
) : Parcelable