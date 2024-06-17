package com.bimabk.testbniapp.core.data

import com.bimabk.testbniapp.core.model.PortoUiModel
import com.bimabk.testbniapp.core.model.PromoResponse
import com.bimabk.testbniapp.utils.dummyPorto
import com.bimabk.testbniapp.core.service.ApiService
import com.bimabk.testbniapp.utils.Resource
import com.bimabk.testbniapp.utils.toPortoUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface AppRepository {
    fun getPromos(): Flow<Resource<List<PromoResponse>>>

    fun getPorto(): Flow<Resource<PortoUiModel>>
}

class AppRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AppRepository {
    override fun getPromos(): Flow<Resource<List<PromoResponse>>> {
        return flow {
            emit(Resource.Loading)
            try {
                val response = api.getPromos()
                emit(Resource.Success(response))
            } catch (e: Throwable) {
                emit(Resource.Error(Throwable()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getPorto(): Flow<Resource<PortoUiModel>> {
        return flow {
            emit(Resource.Loading)
            try {
                val response = toPortoUiModel(dummyPorto)
                emit(Resource.Success(response))
            } catch (e: Throwable) {
                emit(Resource.Error(Throwable()))
            }
        }.flowOn(Dispatchers.IO)
    }
}