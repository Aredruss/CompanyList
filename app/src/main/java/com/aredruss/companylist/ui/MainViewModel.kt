package com.aredruss.companylist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.companylist.domain.CompanyInteractor
import com.aredruss.companylist.ui.company.CompanyState
import com.aredruss.companylist.ui.list.ListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val companyInteractor: CompanyInteractor
) : ViewModel() {

    val companies: MutableLiveData<ListState> by lazy { MutableLiveData<ListState>() }
    val company: MutableLiveData<CompanyState> by lazy { MutableLiveData<CompanyState>() }

    init {
        companies.postValue(ListState.InProgress)
        company.postValue(CompanyState.InProgress)
    }

    fun getCompanyList() {
        companies.postValue(ListState.InProgress)
        viewModelScope.launch(Dispatchers.IO) {
            companies.postValue(
                try {
                    ListState.Success(companyInteractor.getCompanyList())
                } catch (e: Exception) {
                    ListState.Error(e)
                }
            )
        }
    }

    fun getCompany(id: Int) {
        company.postValue(CompanyState.InProgress)
        viewModelScope.launch(Dispatchers.IO) {
            company.postValue(
                try {
                    val company = (companyInteractor.getCompanyById(id)).first()
                    CompanyState.Success(company)
                } catch (e: Exception) {
                    CompanyState.Error(e)
                }
            )
        }
    }
}