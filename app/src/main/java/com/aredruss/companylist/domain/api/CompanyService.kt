package com.aredruss.companylist.domain.api

import com.aredruss.companylist.domain.Company
import com.aredruss.companylist.domain.CompanyListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface CompanyService {
    @GET(".")
    suspend fun getCompanyList(): ArrayList<CompanyListItem>

    @GET(".")
    suspend fun getCompanyById(
        @Query("id") id: Int
    ): ArrayList<Company>
}