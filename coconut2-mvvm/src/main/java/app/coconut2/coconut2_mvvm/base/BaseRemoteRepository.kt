package app.coconut2.coconut2_mvvm.base

import app.coconut2.coconut2_mvvm.network.ApiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.util.Calendar

abstract class BaseRemoteRepository(
//    val apiDao: ApiDao
) {
//    suspend fun <T> saveResponse(
//        data: T
//    ) {
//        val api = ApiEntity(
//            name = "",
//            value = data.toString(),
//            updateDate = Calendar.getInstance().time.toString()
//        )
//        apiDao.insert(api)
//    }

    fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> Response<T>,
        saveResponse: Boolean = false,
    ): Flow<ApiState<T>> = flow {
        try {
            emit(ApiState.Loading())
            val response = apiCall()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    emit(ApiState.Success(data))
//                    saveResponse(data)
                } else {
                    val error = response.errorBody()
                    if (error != null) {
                        emit(ApiState.Error<Nothing>(error.string()))
                    }
                }
            }

        } catch (e: Exception) {
            emit(ApiState.Error<Nothing>(e.message.toString()))
        }
    }.catch { e ->
        emit(ApiState.Error<Nothing>(e.message.toString()))
    }.flowOn(dispatcher)

}