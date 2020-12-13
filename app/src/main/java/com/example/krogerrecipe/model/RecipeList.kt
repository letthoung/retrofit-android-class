package com.example.krogerrecipe.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RecipeList {
    @SerializedName("recipes")
    @Expose
    var recipes: ArrayList<RecipeData> = ArrayList()
}