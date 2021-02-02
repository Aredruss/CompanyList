package com.aredruss.companylist.ui.company

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.aredruss.companylist.R
import com.aredruss.companylist.databinding.FragmentCompanyBinding
import com.aredruss.companylist.domain.CompanyInteractor.Companion.BASE_URL
import com.aredruss.companylist.ui.MainViewModel
import com.aredruss.companylist.util.BaseFragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class CompanyFragment : BaseFragment() {

    private val viewModel: MainViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompanyBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCompany(this.arguments?.getInt("companyId") ?: 1)

        getFragmentBinding<FragmentCompanyBinding>().apply {
            viewModel.company.observe(viewLifecycleOwner, {
                when (it) {
                    is CompanyState.InProgress -> {
                        changeViewsVisibility(this, false)
                        changeErrorVisibility(this, false)
                        changeProgressVisibility(this, true)
                    }
                    is CompanyState.Success -> {
                        changeProgressVisibility(this, false)
                        changeViewsVisibility(this, true)
                        changeErrorVisibility(this, false)

                        nameTv.text = it.data.name
                        descTv.text = it.data.desc

                        if (!it.data.image.isNullOrEmpty()) {
                            Glide.with(root.context)
                                .load(BASE_URL + it.data.image)
                                .into(venueIv)
                        } else venueIv.visibility = View.GONE

                        if (!it.data.phone.isNullOrBlank()) {
                            phoneTv.text = prepareSpan(it.data.phone)
                        } else phoneTv.visibility = View.GONE

                        if (!it.data.website.isNullOrBlank()) {
                            websiteTv.text = prepareSpan(it.data.website)
                        } else websiteTv.visibility = View.GONE
                    }
                    is CompanyState.Error -> {
                        changeProgressVisibility(this, false)
                        changeViewsVisibility(this, false)
                        changeErrorVisibility(this, true)
                        errorTv.text = context?.getString(R.string.error_msg, it.error.javaClass)
                    }
                }

            })
        }

    }

    private fun changeProgressVisibility(binding: FragmentCompanyBinding, isVisible: Boolean) {
        binding.apply {
            progressPb.isVisible = isVisible
        }
    }


    private fun changeViewsVisibility(binding: FragmentCompanyBinding, isVisible: Boolean) {
        binding.apply {
            nameTv.isVisible = isVisible
            phoneTv.isVisible = isVisible
            websiteTv.isVisible = isVisible
            descTv.isVisible = isVisible
            venueIv.isVisible = isVisible
        }
    }

    private fun changeErrorVisibility(binding: FragmentCompanyBinding, isVisible: Boolean) {
        binding.apply {
            errorTv.isVisible = isVisible
        }
    }

    private fun prepareSpan(value: String): SpannableString {
        val spannable = SpannableString(value)
        return spannable.apply {
            setSpan(
                URLSpan(value),
                0,
                value.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}