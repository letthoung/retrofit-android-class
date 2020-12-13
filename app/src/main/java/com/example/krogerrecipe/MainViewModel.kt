package com.example.krogerrecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.krogerrecipe.model.RecipeData
import com.example.krogerrecipe.model.RecipeList
import com.example.krogerrecipe.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<List<RecipeData>>> = MutableLiveData()
    fun getAllRecipes() {
        viewModelScope.launch {
            val response: Response<List<RecipeData>> = repository.getRecipes()
            myResponse.value = response
        }
    }
}