package com.example.krogerrecipe.api

import com.example.krogerrecipe.model.RecipeData
import com.example.krogerrecipe.model.RecipeList
import retrofit2.Response
import retrofit2.http.GET

interface RecipeApi {
    @GET("recipes")
    suspend fun getAllRecipes(): Response<List<RecipeData>>
}