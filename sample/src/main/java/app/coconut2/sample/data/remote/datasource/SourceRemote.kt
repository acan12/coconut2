package app.coconut2.sample.data.remote.datasource

import app.coconut2.sample.data.remote.Api
import javax.inject.Inject

class SourceRemote @Inject constructor(private val api: Api) {
    suspend fun getSourceAsync() =
        api.getDomainNetwork().getTopHeadlines(api.initHeader())
}