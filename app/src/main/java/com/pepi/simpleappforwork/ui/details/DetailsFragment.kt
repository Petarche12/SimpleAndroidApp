package com.pepi.simpleappforwork.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pepi.simpleappforwork.R
import com.pepi.simpleappforwork.databinding.FragmentDetailsBinding
import com.pepi.simpleappforwork.ui.MainActivity
import com.pepi.simpleappforwork.ui.common.BaseFragment
import com.pepi.simpleappforwork.ui.details.DetailsFragmentArgs.Companion.fromBundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels()

    val recipe by lazy {
        fromBundle(requireArguments()).recipeItem
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setBottomNavVisibility(false)
        (requireActivity() as MainActivity).setToolbarVisibility(true)
        setUpView()
        binding.detailsBtnAddToFavourites.setOnClickListener {
            saveRecipeToFavourites()
            findNavController().navigateUp()
        }
        binding.detailsBtnDeleteFromFavourites.setOnClickListener {
            deleteRecipeFromFavourites()
            findNavController().navigateUp()
        }
    }

    private fun saveRecipeToFavourites() {
        viewModel.insertRecipe(recipe)
    }

    private fun deleteRecipeFromFavourites() {
        viewModel.deleteRecipe(recipe)
    }

    private fun setUpView() = with(binding) {
        detailsTitle.text = "Title: ${recipe.title}"
        detailsImageType.text = "Title: ${recipe.imageType}"
        detailsImageUrl.text = "Title: ${recipe.image}"
        view?.let {
            Glide.with(it)
                .load(recipe.image)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(detailsImageView)
        }
    }
}
