package app.coconut2.sample.domain.repo.headline

import app.coconut2.sample.domain.mapper.HeadlineData

interface IHeadlineRepository {
    suspend fun getSourcegetHeadlineDataAsync(): List<HeadlineData?>
}