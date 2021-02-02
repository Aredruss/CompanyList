package com.aredruss.companylist.util

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment : Fragment() {

    protected var binding: ViewBinding? = null

    fun <T : ViewBinding> getFragmentBinding(): T {
        return binding as T
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}