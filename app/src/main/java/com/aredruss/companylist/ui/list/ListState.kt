package com.aredruss.companylist.ui.list

import com.aredruss.companylist.domain.CompanyListItem

sealed class ListState {
    object InProgress : ListState()
    class Success(val data: ArrayList<CompanyListItem>) : ListState()
    class Error(val error: Throwable) : ListState()
}
