package com.example.krogerrecipe.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class RecipeData(
    @SerializedName("recipeName")
    @Expose
    val recipeName: String,
    @SerializedName("recipeBriefDescription")
    @Expose
    val recipeBriefDescription: String,
    @SerializedName("recipeDetailDescription")
    @Expose
    var recipeDetailDescription: String,
    @SerializedName("recipeDifficulty")
    @Expose
    val recipeDifficulty: String,
    @SerializedName("recipeImage")
    @Expose
    val recipeImage: String
)