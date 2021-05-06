package com.pepi.simpleappforwork.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pepi.simpleappforwork.R
import com.pepi.simpleappforwork.common.util.Resource
import com.pepi.simpleappforwork.common.util.exhaustive
import com.pepi.simpleappforwork.common.util.initiateSimpleRecycleView
import com.pepi.simpleappforwork.data.model.Recipe
import com.pepi.simpleappforwork.databinding.FragmentFavouritesBinding
import com.pepi.simpleappforwork.ui.MainActivity
import com.pepi.simpleappforwork.ui.common.BaseFragment
import com.pepi.simpleappforwork.ui.common.InteractionInterface
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>(R.layout.fragment_favourites),
    InteractionInterface {

    private val favouritesViewModel: FavouritesViewModel by viewModels()
    private val myAdapter = FavouriteRecipeAdapter(this)

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavouritesBinding = FragmentFavouritesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setBottomNavVisibility(true)
        (requireActivity() as MainActivity).setToolbarVisibility(true)

        binding.favouriteRecyclerView.initiateSimpleRecycleView(
            myAdapter,LinearLayoutManager(context),true
        )

        favouritesViewModel.favouriteRecipes.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    myAdapter.submitList(it.data)
                }
            }.exhaustive
        }
    }

    override fun onItemSelected(position: Int, item: Recipe) {
        findNavController().navigate(
            FavouritesFragmentDirections.actionHomeFragment2ToDetailsFragment(
                item
            )
        )
    }

}