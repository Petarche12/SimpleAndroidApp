package com.pepi.simpleappforwork.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pepi.simpleappforwork.R
import com.pepi.simpleappforwork.databinding.FragmentSettingsBinding
import com.pepi.simpleappforwork.ui.MainActivity
import com.pepi.simpleappforwork.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>(R.layout.fragment_settings) {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setBottomNavVisibility(false)
        (requireActivity() as MainActivity).setToolbarVisibility(true)
    }

}