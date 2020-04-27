package com.android.testapp.data.network

import com.android.testapp.data.network.model.CornApiModel
import retrofit2.http.*

interface ApiService {
    @GET("AgileRider/api/GetCornFieldsByDevice")
    suspend fun getCorns(@Query("dev_ID") deviceId: String): List<CornApiModel>
}