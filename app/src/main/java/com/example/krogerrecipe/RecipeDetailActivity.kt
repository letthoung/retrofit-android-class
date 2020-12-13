package com.example.krogerrecipe

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_recipe_detail.*


class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var recipeDescription: TextView
    private lateinit var recipeImage: ImageView
    private var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        supportActionBar?.apply {
            title = getString(R.string.recipe_detail)
            setDisplayHomeAsUpEnabled(true)
        }

        recipeDescription = recipeDetailDescription
        recipeImage = recipeDetailImage

        intent.extras?.let{
            pos = it.getInt("position")
            val recipe = AllRecipes.list[pos]
            recipeImage.setImageResource(
                this.resources.getIdentifier(recipe.recipeImage,
                    "drawable",this.packageName)
            )
            recipeDetailTitle.text = recipe.recipeName
            recipeDescription.text = recipe.recipeDetailDescription
        }

        recipeDescription.setMovementMethod(ScrollingMovementMethod())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        recipeDescription.setText(AllRecipes.list[pos].recipeDetailDescription)
    }

    fun editRecipeDescription(view: View) {
        var intent = Intent(this, RecipeEditActivity::class.java)
        intent.putExtra(EXTRA_POSITION, pos)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_POSITION = "position"
    }
}