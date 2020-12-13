package com.example.krogerrecipe

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.krogerrecipe.model.RecipeData
import com.example.krogerrecipe.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recipesRecyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: RecipeAdapter
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var recipeList: ArrayList<RecipeData>
    private lateinit var loadingIndicator: ProgressBar
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllRecipes()
        viewModel.myResponse.observe(this, androidx.lifecycle.Observer {
                response ->
            if(response.isSuccessful) {
                response.body()?.forEach {
                    AllRecipes.list += it
                }
                recipeList = AllRecipes.list as ArrayList<RecipeData>;

                // set adapter
                adapter = RecipeAdapter(this, recipeList)
                recipesRecyclerView.setAdapter(adapter);

                finishLoading()
            }
            else {
                Log.i("Response", response.errorBody().toString())
            }
        })

        // initialize UI elements
        loadingIndicator = recipesLoadingIndicator
        recipesRecyclerView = allRecipesRecyclerView

        // set up layout manager for recycler view of recipes
        val orientation = resources.configuration.orientation
        val sw = resources.configuration.smallestScreenWidthDp
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            // In portrait
            if(sw < 600) { // phone
                layoutManager = LinearLayoutManager(this)
            } else { // tablet
                layoutManager = GridLayoutManager(this, 2)
            }
        } else {
            // In landscape
            if (sw < 600){ // phone
                layoutManager = GridLayoutManager(this, 2)
            } else { // tablet
                layoutManager = GridLayoutManager(this, 4)
            }
        }
        recipesRecyclerView.layoutManager = layoutManager;

        // set up action bar
        toggle = ActionBarDrawerToggle(this, mainDrawerLayout, R.string.open, R.string.close)
        mainDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // enable up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // set up listeners for options in navigation drawer
        mainNavView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menuItemAbout -> startActivity(Intent(this, AboutActivity::class.java))
                R.id.menuRecipes -> startActivity(Intent(this, MainActivity::class.java))
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    fun startLoading(){
        // fetch recipe data list
        recipeList = AllRecipes.list as ArrayList<RecipeData>;

        // set adapter
        adapter = RecipeAdapter(this, recipeList)
        recipesRecyclerView.setAdapter(adapter);

        loadingIndicator.visibility = View.VISIBLE
        recipesRecyclerView.visibility = View.INVISIBLE
    }

    fun finishLoading(){
        loadingIndicator.visibility = View.GONE
        recipesRecyclerView.visibility = View.VISIBLE
    }
}