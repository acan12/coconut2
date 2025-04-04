package app.coconut2.sample.data.remote.repo

import app.coconut2.coconut2_mvvm.base.BaseRepository
import app.coconut2.sample.data.remote.Api
import app.coconut2.sample.domain.repo.headline.IHeadlineRepository
import javax.inject.Inject

class HeadlineRepository @Inject constructor(private val api: Api) : BaseRepository(),
    IHeadlineRepository {


    override suspend fun getSourcegetHeadlineDataAsync() =
        safeApiCall(
            apiCall = {
                api.getDomainNetwork().getTopHeadlines(api.initHeader())
            },
            saveResponse = true,
        )


}