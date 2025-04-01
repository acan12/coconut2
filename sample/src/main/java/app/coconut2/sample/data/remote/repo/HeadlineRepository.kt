package app.coconut2.sample.data.remote.repo

import app.coconut2.coconut2_mvvm.base.BaseRemoteRepository
import app.coconut2.sample.data.remote.Api
import app.coconut2.sample.domain.repo.headline.IHeadlineRepository
import javax.inject.Inject

class HeadlineRepository @Inject constructor(private val api: Api) : BaseRemoteRepository(),
    IHeadlineRepository {


    override suspend fun getSourcegetHeadlineDataAsync() =
        safeApiCall(
            apiCall = {
                api.getDomainNetwork().getTopHeadlines(api.initHeader())
            },
            saveResponse = true,
        )


}