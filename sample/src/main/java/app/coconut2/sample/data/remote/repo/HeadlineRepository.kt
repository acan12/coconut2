package app.coconut2.sample.data.remote.repo

import app.coconut2.sample.data.remote.Api
import app.coconut2.sample.domain.mapper.toHeadlineData
import app.coconut2.sample.domain.repo.headline.IHeadlineRepository
import javax.inject.Inject

class HeadlineRepository @Inject constructor(private val api: Api) : IHeadlineRepository {

    override suspend fun getSourcegetHeadlineDataAsync() = let {
        val data = api.getDomainNetwork().getTopHeadlines(api.initHeader())
        val dataMapper = data.sources.map {
            it.toHeadlineData()
        }

        dataMapper
    }


}