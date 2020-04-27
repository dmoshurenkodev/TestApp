package com.android.testapp.presentation.corns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.testapp.base.BaseViewModel
import com.android.testapp.base.utils.UIState
import com.android.testapp.domain.interactor.GetCornListInteractor
import com.android.testapp.domain.model.CornModel

class CornsViewModel(val getCornList: GetCornListInteractor) : BaseViewModel() {

    private var cornsList = listOf<CornModel>()
    private val _corns = MutableLiveData<UIState<List<CornModel>>>()
    val corns: LiveData<UIState<List<CornModel>>>
        get() = _corns

    init {
        loadDate()
    }

    fun filterCorns(search: String) {
        val corns = cornsList.filter {
            it.fieldNoDescr.contains(search, true) ||
                    it.fieldNo.contains(search, true) ||
                    it.cornType.contains(search, true) ||
                    it.farmName.contains(search, true)
        }
        _corns.value = UIState.Success(corns)
    }

    private fun loadDate() = runCoroutine(exceptionHandler = {
        _corns.value = UIState.Failure
        true
    }) {
        _corns.value = UIState.Loading
        val corns = getCornList()
        cornsList = corns.toList()
        if (corns.isNullOrEmpty()) {
            _corns.value = UIState.Empty
            return@runCoroutine
        }
        _corns.value = UIState.Success(corns)
    }
}