package com.android.testapp.data.network.datasource

import com.android.testapp.BuildConfig
import com.android.testapp.data.network.ApiService
import com.android.testapp.data.network.mapper.CornFromApiMapper
import com.android.testapp.domain.model.CornModel

class NetworkDataSourceImpl(
    private val apiService: ApiService,
    private val cornFromMapper: CornFromApiMapper
) : NetworkDataSource {
    override suspend fun getCorn(): List<CornModel> {
        return cornFromMapper.map(apiService.getCorns(BuildConfig.DEVICE_ID))
    }
}