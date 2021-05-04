package com.pepi.simpleappforwork.ui.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pepi.simpleappforwork.data.Recipe
import com.pepi.simpleappforwork.databinding.RecipeViewBinding

class Adapter(private val interaction: Interaction? = null) :
    PagingDataAdapter<Recipe, Adapter.RecipeHolder>(DiffUtilCallback()) {

    class DiffUtilCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {

        return RecipeHolder(
            RecipeViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        getItem(position).let {
            if (it != null) {
                holder.bind(it)
            }
        }
    }

    inner class RecipeHolder
    constructor(
        private val binding: RecipeViewBinding, //Change with adequate binding
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION && interaction != null) {
                    val item = getItem(position)
                    if (item != null) {
                        interaction.onItemSelected(position, item)
                    }
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

    interface Interaction {
        fun onItemSelected(position: Int, item: Recipe)
    }
}