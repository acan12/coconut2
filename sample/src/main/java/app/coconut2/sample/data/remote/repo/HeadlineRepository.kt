package app.coconut2.sample.data.remote.repo

import android.util.Log
import app.coconut2.coconut2_mvvm.base.BaseRepository
import app.coconut2.coconut2_mvvm.core.DataType
import app.coconut2.sample.data.local.source.TopHeadlineLocal
import app.coconut2.sample.data.remote.Api
import app.coconut2.sample.domain.repo.headline.IHeadlineRepository
import javax.inject.Inject

class HeadlineRepository @Inject constructor(
    private val api: Api,
    private val topHeadlineLocalData: TopHeadlineLocal,
) : BaseRepository(), IHeadlineRepository {

    override suspend fun getSourcegetHeadlineDataAsync() =
        safeApiCall(
            apiCall = {
                api.getDomainNetwork().getTopHeadlines(api.initHeader())
            },
            saveResponse = { response ->
                topHeadlineLocalData.save(response, DataType.JSON)
                topHeadlineLocalData.get().collect {
                    val data = it
                    Log.d("TAG", data.sources.size.toString())
                }
            },
        )
}