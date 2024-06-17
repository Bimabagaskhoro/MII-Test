package com.bimabk.testbniapp.core.service

import com.bimabk.testbniapp.core.model.PromoResponse
import retrofit2.http.GET

interface ApiService {
    @GET("promos")
    suspend fun getPromos(): List<PromoResponse>
}