package com.aredruss.companylist.ui.company

import com.aredruss.companylist.domain.Company

sealed class CompanyState {
    object InProgress : CompanyState()
    class Success(val data: Company) : CompanyState()
    class Error(val error: Throwable) : CompanyState()
}