package com.android.testapp.data.repository

import com.android.testapp.data.network.datasource.NetworkDataSource
import com.android.testapp.domain.model.CornModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CornRepository(private val networkDataSource: NetworkDataSource) {

    suspend fun getCorns(): List<CornModel> = withContext(Dispatchers.IO) {
        return@withContext networkDataSource.getCorn()
    }

}