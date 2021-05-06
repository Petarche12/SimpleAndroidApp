package com.pepi.simpleappforwork.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pepi.simpleappforwork.data.model.Recipe

@Database(entities = [Recipe::class],version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao
}