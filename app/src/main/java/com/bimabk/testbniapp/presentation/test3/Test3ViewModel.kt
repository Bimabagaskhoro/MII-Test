package com.bimabk.testbniapp.presentation.test3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimabk.testbniapp.core.data.AppRepository
import com.bimabk.testbniapp.core.model.PortoUiModel
import com.bimabk.testbniapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Test3ViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    private val _porto = MutableStateFlow<Resource<PortoUiModel>>(Resource.Default)
    val porto = _porto.asStateFlow()

    init {
        getPorto()
    }

    private fun getPorto() = viewModelScope.launch {
        repository.getPorto().collect {
            _porto.value = it
        }
    }
}