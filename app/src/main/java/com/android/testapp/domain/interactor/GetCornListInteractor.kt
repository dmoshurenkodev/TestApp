package com.android.testapp.domain.interactor

import com.android.testapp.data.repository.CornRepository
import com.android.testapp.domain.model.CornModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.android.testapp.base.interactor.InteractorWithoutParams

class GetCornListInteractor(private val cornRepository: CornRepository) :
    InteractorWithoutParams<List<CornModel>> {
    override suspend fun invoke(): List<CornModel> = withContext(Dispatchers.Default) {
        return@withContext cornRepository.getCorns()
    }
}