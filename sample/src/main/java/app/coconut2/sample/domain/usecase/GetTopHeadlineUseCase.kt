package app.coconut2.sample.domain.usecase

import app.coconut2.coconut2_mvvm.network.ApiState
import app.coconut2.sample.data.remote.repo.HeadlineRepository
import app.coconut2.sample.data.remote.response.TopHeadlineResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopHeadlineUseCase @Inject constructor
    (val repository: HeadlineRepository) {
    suspend operator fun invoke(): Flow<ApiState<TopHeadlineResponse>> =
        repository.getSourcegetHeadlineDataAsync()
}