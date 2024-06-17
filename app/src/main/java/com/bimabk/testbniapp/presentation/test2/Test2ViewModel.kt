package com.bimabk.testbniapp.presentation.test2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimabk.testbniapp.core.data.AppRepository
import com.bimabk.testbniapp.core.model.PromoResponse
import com.bimabk.testbniapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Test2ViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {
    private val _getPromos = MutableStateFlow<Resource<List<PromoResponse>>>(Resource.Default)
    val getPromos = _getPromos.asStateFlow()

    init {
        getPromos()
    }

    fun getPromos() = viewModelScope.launch {
        repository.getPromos().collect {
            _getPromos.value = it
        }
    }
}