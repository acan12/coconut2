//package app.coconut2.coconut2_mvvm.base
//
//import com.bumptech.glide.load.engine.Resource
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.flatMapLatest
//import kotlinx.coroutines.withContext
//
//abstract class BaseUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
//    suspend operator fun invoke(parameters: P): Resource<R> {
//        return try {
//            withContext(coroutineDispatcher) {
//                execute(parameters).let {
//                    Resource.Success(it)
//                }
//            }
//        } catch (e: Exception) {
//            Resource.Error(e.toString())
//        }
//    }
//
//    @Throws(RuntimeException::class)
//    protected abstract suspend fun execute(parameters: P): R
//}