package app.coconut2.sample.data.remote.repo

import app.coconut2.sample.data.remote.Api
import javax.inject.Inject

class SourceRepository @Inject constructor(private val api: Api) {
    suspend fun getSourceAsync() =
        api.getDomainNetwork().getTopHeadlines(api.initHeader())
}