package com.aredruss.companylist.ui.list

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.aredruss.companylist.domain.CompanyListItem

class ListDiffCallback(
    private val newList: ArrayList<CompanyListItem>,
    private val oldList: ArrayList<CompanyListItem>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition].id == newList[newPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (id, name, _) = oldList[oldPosition]
        val (id1, name1, _) = newList[newPosition]

        return name == name1 && id == id1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}