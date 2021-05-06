package com.pepi.simpleappforwork.ui.favourites

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pepi.simpleappforwork.data.model.Recipe
import com.pepi.simpleappforwork.databinding.RecipeViewBinding
import com.pepi.simpleappforwork.ui.common.InteractionInterface

class FavouriteRecipeAdapter(private val interaction: InteractionInterface? = null) :
    ListAdapter<Recipe, FavouriteRecipeAdapter.FavouriteRecipeHolder>(DiffUtilCallback()) {

    class DiffUtilCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteRecipeHolder {

        return FavouriteRecipeHolder(
            RecipeViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: FavouriteRecipeHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    inner class FavouriteRecipeHolder
    constructor(
        private val binding: RecipeViewBinding, //Change with adequate binding
        private val interaction: InteractionInterface?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION && interaction != null) {
                    val item = getItem(position)
                    interaction.onItemSelected(position, item)
                }
            }
        }

        fun bind(item: Recipe) = with(binding) {
            Glide.with(itemView)
                .load(item.image)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
            text.text = item.title
        }
    }

}