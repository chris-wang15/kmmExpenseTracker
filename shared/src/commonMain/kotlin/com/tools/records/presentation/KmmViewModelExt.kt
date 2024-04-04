package com.tools.records.presentation

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.launch

fun KMMViewModel.postEvent(event: Any) {
    viewModelScope.coroutineScope.launch {
        EventBus.post(event)
    }
}