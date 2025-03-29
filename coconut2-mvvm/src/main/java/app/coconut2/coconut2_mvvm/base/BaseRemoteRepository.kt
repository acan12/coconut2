//package app.coconut2.coconut2_mvvm.base
//
//import app.coconut2.coconut2_mvvm.network.ApiState
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.flowOn
//import retrofit2.Response
//
//abstract class BaseRemoteRepository {
//    suspend fun <T> safeApiCall(
//        dispatcher: CoroutineDispatcher = Dispatchers.IO,
//        apiCall: suspend () -> Response<T>
//    ): Flow<ApiState<T>> = flow {
//        emit(ApiState.Loading())
//        val response = apiCall()
//        if (response.isSuccessful) {
//            val data = response.body()
//            if (data != null) {
//                emit(ApiState.Success(data))
//            } else {
//                val error = response.errorBody()
//                if (error != null) {
//                    emit(ApiState.Error(error.string()))
//                }
//            }
//        } else {
//            emit(ApiState.Error(response.errorBody().toString()))
//
//        }
//    }.catch { e -> emit(ApiState.Error(e.message.toString()))
//    }.flowOn(dispatcher)
//
//}