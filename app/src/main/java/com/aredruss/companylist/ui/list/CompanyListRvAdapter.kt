package com.aredruss.companylist.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aredruss.companylist.databinding.ListItemCompanyBinding
import com.aredruss.companylist.domain.CompanyListItem

class CompanyListRvAdapter(
    private var companies: ArrayList<CompanyListItem>,
    private val open: (id: Int) -> Unit
) : RecyclerView.Adapter<CompanyListRvAdapter.CompanyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = ListItemCompanyBinding
            .inflate(
                LayoutInflater
                    .from(parent.context), parent, false
            )
        return CompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.onBind(companies.get(position))
    }

    override fun getItemCount() = companies.size

    fun setCompanies(companies: ArrayList<CompanyListItem>) {
        val diffCallback = ListDiffCallback(companies, this.companies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.companies.clear()
        this.companies.addAll(companies)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class CompanyViewHolder(
        val binding: ListItemCompanyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(company: CompanyListItem) {
            binding.nameTv.text = company.name
            binding.root.setOnClickListener {
                open(company.id)
            }
        }
    }

}