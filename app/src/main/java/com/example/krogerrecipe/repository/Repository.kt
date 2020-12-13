package com.example.krogerrecipe.repository

import com.example.krogerrecipe.api.RetrofitInstance
import com.example.krogerrecipe.model.RecipeData
import com.example.krogerrecipe.model.RecipeList
import retrofit2.Call
import retrofit2.Response

class Repository {
    suspend fun getRecipes(): Response<List<RecipeData>> {
        return RetrofitInstance.api.getAllRecipes()
    }
}