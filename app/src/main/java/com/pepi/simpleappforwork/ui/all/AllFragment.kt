package com.pepi.simpleappforwork.ui.all

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
import com.pepi.simpleappforwork.databinding.FragmentAllBinding
import com.pepi.simpleappforwork.ui.MainActivity
import com.pepi.simpleappforwork.ui.common.BaseFragment
import com.pepi.simpleappforwork.ui.common.InteractionInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : BaseFragment<FragmentAllBinding>(R.layout.fragment_all),
    InteractionInterface {

    private val viewModel: AllViewModel by viewModels()
    private val myAdapter: RecipesAdapter = RecipesAdapter(this)

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAllBinding = FragmentAllBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setBottomNavVisibility(true)
        (requireActivity() as MainActivity).setToolbarVisibility(true)

        binding.searchRecyclerView.initiateSimpleRecycleView(
            myAdapter,LinearLayoutManager(context),true
        )
        viewModel.recipes.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                }
                is Resource.Loading -> {
                }
                is Resource.Success ->
                    it.data?.let { it1 -> myAdapter.submitData(viewLifecycleOwner.lifecycle, it1) }
            }.exhaustive
        }
    }

    override fun onItemSelected(position: Int, item: Recipe) {
        findNavController().navigate(AllFragmentDirections.actionAllFragmentToDetailsFragment(item))
    }
}