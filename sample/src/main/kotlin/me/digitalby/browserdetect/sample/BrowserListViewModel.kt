package me.digitalby.browserdetect.sample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.digitalby.browserdetect.BrowserDetector
import me.digitalby.browserdetect.BrowserInfo

sealed interface BrowserListState {
    data object Loading : BrowserListState

    data class Loaded(
        val browsers: List<BrowserInfo>,
    ) : BrowserListState
}

class BrowserListViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val detector = BrowserDetector.create(application)

    private val _state = MutableStateFlow<BrowserListState>(BrowserListState.Loading)
    val state: StateFlow<BrowserListState> = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        _state.value = BrowserListState.Loading
        viewModelScope.launch {
            _state.value = BrowserListState.Loaded(detector.detectAsync())
        }
    }
}
