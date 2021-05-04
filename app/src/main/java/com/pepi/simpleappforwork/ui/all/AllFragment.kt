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
import com.pepi.simpleappforwork.data.Recipe
import com.pepi.simpleappforwork.databinding.FragmentSearchBinding
import com.pepi.simpleappforwork.ui.MainActivity
import com.pepi.simpleappforwork.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search),
    RecipiesAdapter.Interaction {

    private val viewModel: AllViewModel by viewModels()
    private val myAdapter: RecipiesAdapter = RecipiesAdapter(this)

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setBottomNavVisibility(true)
        (requireActivity() as MainActivity).setToolbarVisibility(true)

        binding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
            setHasFixedSize(true)
        }
        viewModel.recipes.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                }
                is Resource.Loading -> {
                }
                is Resource.Success ->
                    it.data?.let { it1 -> myAdapter.submitData(viewLifecycleOwner.lifecycle, it1) }
            }
        }
    }

    override fun onItemSelected(position: Int, item: Recipe) {
        findNavController().navigate(AllFragmentDirections.actionAllFragmentToDetailsFragment(item))
    }
}