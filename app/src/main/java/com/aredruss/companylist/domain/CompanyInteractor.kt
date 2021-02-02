package com.aredruss.companylist.domain

import com.aredruss.companylist.domain.api.CompanyService


class CompanyInteractor(
    private val companyService: CompanyService
) {
    companion object {
        val BASE_URL = "https://lifehack.studio/test_task/"
        val BASE_URL_TEST = BASE_URL + "test.php/"
    }

    suspend fun getCompanyList() = companyService.getCompanyList()
    suspend fun getCompanyById(id: Int) = companyService.getCompanyById(id)
}