package com.pepi.simpleappforwork.ui.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pepi.simpleappforwork.R
import com.pepi.simpleappforwork.databinding.FragmentSearchBinding
import com.pepi.simpleappforwork.ui.MainActivity
import com.pepi.simpleappforwork.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val viewModel: AllViewModel by viewModels()
    private val myAdapter: Adapter = Adapter(null)

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
            it.data?.let { it1 -> myAdapter.submitData(viewLifecycleOwner.lifecycle, it1) }
        }
    }
}