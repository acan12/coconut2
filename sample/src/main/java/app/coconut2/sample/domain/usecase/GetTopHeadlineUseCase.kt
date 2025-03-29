package app.coconut2.sample.domain.usecase

import app.coconut2.coconut2_mvvm.network.ApiState
import app.coconut2.sample.data.remote.repo.HeadlineRepository
import app.coconut2.sample.domain.mapper.HeadlineData
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTopHeadlineUseCase @Inject constructor(val repository: HeadlineRepository) {
    operator fun invoke(): Flow<ApiState<List<HeadlineData?>>> =
        flow {
            try {
                emit(ApiState.Loading())
                val data = repository.getSourcegetHeadlineDataAsync()

                emit(ApiState.Success(data))
            } catch (e: Exception) {
                emit(ApiState.Error<Nothing>(e.message.toString()))
            }

        }.flowOn(Dispatchers.IO)

}