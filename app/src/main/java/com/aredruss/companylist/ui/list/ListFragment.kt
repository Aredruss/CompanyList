package com.aredruss.companylist.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aredruss.companylist.R
import com.aredruss.companylist.databinding.FragmentListBinding
import com.aredruss.companylist.ui.MainViewModel
import com.aredruss.companylist.util.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var companyListRvAdapter: CompanyListRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFragmentBinding<FragmentListBinding>().apply {
            setupRecyclerView(this)
            viewModel.getCompanyList()
            viewModel.companies.observe(viewLifecycleOwner, {
                when (it) {
                    is ListState.InProgress -> {
                        changeViewsVisibility(this, false)
                        changeErrorVisibility(this, false)
                        changeProgressVisibility(this, true)
                    }
                    is ListState.Success -> {
                        changeViewsVisibility(this, true)
                        changeErrorVisibility(this, false)
                        changeProgressVisibility(this, false)
                        companyListRvAdapter.setCompanies(it.data)
                    }
                    is ListState.Error -> {
                        changeViewsVisibility(this, false)
                        changeErrorVisibility(this, true)
                        changeProgressVisibility(this, false)
                        errorTv.text = context?.getString(R.string.error_msg, it.toString())
                    }
                }
            })

            refreshSrl.setOnRefreshListener {
                viewModel.getCompanyList()
                refreshSrl.isRefreshing = false
            }
        }
    }

    private fun openCompanyFragment(id: Int) {
        val navController = findNavController()
        val action = ListFragmentDirections.actionListFragmentToCompanyFragment(id)
        navController.navigate(action)
    }

    private fun setupRecyclerView(binding: FragmentListBinding) {
        binding.apply {
            companyListRvAdapter = CompanyListRvAdapter(
                ArrayList(),
                this@ListFragment::openCompanyFragment
            )
            companyListRv.adapter = companyListRvAdapter
            companyListRv.layoutManager = LinearLayoutManager(root.context)
            companyListRv.addItemDecoration(
                DividerItemDecoration(
                    companyListRv.context, DividerItemDecoration.HORIZONTAL
                )
            )
        }
    }

    private fun changeProgressVisibility(binding: FragmentListBinding, isVisible: Boolean) {
        binding.apply {
            progressPb.isVisible = isVisible
        }
    }


    private fun changeViewsVisibility(binding: FragmentListBinding, isVisible: Boolean) {
        binding.apply {
            companyListRv.isVisible = isVisible
        }
    }

    private fun changeErrorVisibility(binding: FragmentListBinding, isVisible: Boolean) {
        binding.apply {
            errorTv.isVisible = isVisible
        }
    }
}