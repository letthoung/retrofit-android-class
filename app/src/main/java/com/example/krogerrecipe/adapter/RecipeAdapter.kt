package com.example.krogerrecipe

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.krogerrecipe.model.RecipeData

class RecipeAdapter(private val context: Context, recipeDataList: List<RecipeData>) :
    RecyclerView.Adapter<RecipeViewHolder>() {
    private val recipeDataList: List<RecipeData>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.image.setImageResource(
            this.context.resources.getIdentifier(recipeDataList[position].recipeImage,
                "drawable",this.context.packageName)
        )
        holder.title.setText(recipeDataList[position].recipeName)
        holder.description.setText(recipeDataList[position].recipeBriefDescription)
        holder.difficulty.setText(recipeDataList[position].recipeDifficulty)

        holder.mCardView.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, RecipeDetailActivity::class.java)
            intent.putExtra("position", holder.adapterPosition)
            Log.d("abc", holder.adapterPosition.toString())
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return recipeDataList.size
    }

    init {
        this.recipeDataList = recipeDataList
    }
}

class RecipeViewHolder(itemView: View) : ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.recipeBriefImage)
    val title: TextView = itemView.findViewById(R.id.recipeBriefTitle)
    val description: TextView = itemView.findViewById(R.id.recipeBriefDescription)
    val difficulty: TextView = itemView.findViewById(R.id.recipeBriefDifficulty)
    val mCardView: CardView = itemView.findViewById(R.id.recipeBriefCardView)
}