package com.android.testapp.data.network.datasource

import com.android.testapp.domain.model.CornModel

interface NetworkDataSource {
    suspend fun getCorn() : List<CornModel>
}