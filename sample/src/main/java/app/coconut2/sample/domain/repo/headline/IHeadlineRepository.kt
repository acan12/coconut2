package app.coconut2.sample.domain.repo.headline

import app.coconut2.coconut2_mvvm.network.ApiState
import app.coconut2.sample.data.remote.response.TopHeadlineResponse
import kotlinx.coroutines.flow.Flow

interface IHeadlineRepository {
    suspend fun getSourcegetHeadlineDataAsync(): Flow<ApiState<TopHeadlineResponse>>
}