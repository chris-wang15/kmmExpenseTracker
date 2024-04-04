package com.tools.records.presentation

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class CategoryTabContainerVM : KMMViewModel() {
    private val _selectedCategoryId = MutableStateFlow(
        viewModelScope,
        0L
    )

    @NativeCoroutinesState
    val selectedCategoryId: StateFlow<Long> = _selectedCategoryId.asStateFlow()

    // needs import com.rickclephas.kmm.viewmodel.stateIn
    @NativeCoroutinesState
    val isBottomSheetShowing: StateFlow<Boolean> = _selectedCategoryId
        .map { it > 0 }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            false
        )

    fun changeSelectedId(value: Long) {
        _selectedCategoryId.tryEmit(value)
    }
}