# Coconut2 

Model-View-ViewModel (MVVM) is a software design pattern that separates an application into three parts: the model, view, and view model. This separation improves maintainability and testability. 

coconut2 use mvvm architecture that contains 3 layer:
- data 
- domain 
- screen + viewModel

<!-- GETTING STARTED -->
## Getting Started

This is a library as base code references for android project with use MVVM architecture by follow clean architecture concept. You can follow instructions on setting up your project locally, to make sure you follow the right way to optimize all benefit from this library.

<!-- SETUP DIRECTORY -->
### Setup your project directory
base on clean architecture , we need to setup project directory representatif , for easy to us detact where issue being located , represented with the function project expected.


### Project directory 

- **data** : representation of data factory and data manipulation which will produce data in model object and will passing to the next layer
- **domain** : hub data for handle data source into and from ui / presentation layer
- **ui** : reactive programming is using to fetch data source from domain , buffer inside viewModel and the activity or fragment will consume data to present into ui screen
- **core** : places of all static function to utilize data management, hardware, tools, and not dependent with any layer before.
  
    <img width="301" alt="Screenshot 2025-04-08 at 14 11 36" src="https://github.com/user-attachments/assets/6b62b79f-c704-4ea1-9529-821f4862c0fd" />

## Usage

Just follow instruction below, for integration

### 1. gradle script

Add dependency
```sh
dependencies {
    implementation("com.google.dagger:hilt-android:2.55")
    ksp("com.google.dagger:hilt-compiler:2.55")
    implementation("com.github.acan12:coconut2:<RELEASE-TAG-VERSION>")
}
```

### 2. Setup Code Base
AndroidManifest.xml
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:name=".App"
        android:allowBackup="true"
    ....
    ....
```

App.kt extends BaseApp
```java
import app.coconut2.coconut2_mvvm.base.BaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : BaseApp()
```

Activity or Fragment
```java
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject lateinit var connectionManager: ConnectionManager
    ...
```

View Model
```java
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopHeadlineUseCase: GetTopHeadlineUseCase, // <- inject usecase fetch data from remote datasource
    private val getUserUseCase: GetUserUseCase, // <- inject usecase fetch data from local datasource
) : ViewModel() {}

```

UseCase
```java
class GetTopHeadlineUseCase @Inject constructor(val repository: IHeadlineRepository) {
    suspend operator fun invoke(): Flow<ApiState<TopHeadlineResponse>> =
        repository.getSourcegetHeadlineDataAsync()
}
```

Repository
```java
class HeadlineRepository @Inject constructor(
    private val api: Api, // <- inject api object
    private val topHeadlineLocalData: TopHeadlineLocalData, // <- inject local data source
) : BaseRepository(), IHeadlineRepository {

    // implement save local data from api response
    override suspend fun getSourcegetHeadlineDataAsync() =
        safeApiCall(
            apiCall = {
                api.getDomainNetwork().getTopHeadlines(api.initHeader())
            },
            saveResponse = { response ->
                topHeadlineLocalData.save(response, DataType.JSON) // save data response into local cache in json string format
                topHeadlineLocalData.get().collect {
                    val data = it // fetch data response from local cache
                    Log.d("TAG", data.sources.size.toString())
                }
            },
        )
}
```

```java
interface IHeadlineRepository {
    suspend fun getSourcegetHeadlineDataAsync(): Flow<ApiState<TopHeadlineResponse>>
}
```
```java
// Special function
safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO, // -> as default , if want to use Main just change into "Dispatchers.Main"
    apiCall = {
        api.getDomainNetwork().getTopHeadlines(api.initHeader())  // -> call remote api call
    },
    saveResponse = { response ->
        ....
        ....
    },
)
```

ApiService
```java
interface ApiService {

    @GET("top-headlines/sources?apiKey=${BuildConfig.NEWSORG_APIKEY}")
    suspend fun getTopHeadlines(@HeaderMap header: Map<String, String>): Response<TopHeadlineResponse>

}
```

## Features Available

List of feature will becoming as tools for doing development.

#### 1. Custom Database Integration

the code base provide for developer to custom database module by registered inside dependency injection (DI).

```java
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = LocalDatabaseBuilder(
        context,
        <Database Class>::class.java,
        BuildConfig.DB_NAME,
    ).build()

    // inject instant object api 
    @Provides
    @Singleton
    fun provideApi(): Api = Api(ApiManager())

    // inject instant object connection manager interact with network
    @Provides
    @Singleton
    fun provideConnectionChecker(@ApplicationContext context: Context): ConnectionManager =
        ConnectionManager(context)
}
```

#### 2. Custom Retrofit 

Integrating your custom retrofit interception in network module as part of code base

```java
open class Api @Inject constructor(val apiManager: IApiManager) {

    fun initHeader(): Map<String, String> {
        val map = HashMap<String, String>()
        map["Cache-Control"] = "no-store"
        map["Content-Type"] = "application/json"
        return map
    }

    fun getDomainNetwork(): ApiService =
        ApiBuilder.build(
            apiDomain = BuildConfig.SERVER_URL, // base url
            allowUntrusted = true, // ssl security
            apiService = <Interface ApiService>::class.java,
            timeOut = 300,
            enableLogging = BuildConfig.DEBUG,
            apiManager = apiManager
        ) as ApiService
}
```

#### 3. Network Status 

Network status in ***ApiState*** give information for being handled in *viewmodel*

*ApiState*
```java
sealed class ApiState<out T> {
    data class Success<out T>(val data: T) : ApiState<T>() // Success -> network status ok and get 200 response callback
    data class Error<S>(val message: String?, val data: S? = null): ApiState<Nothing>() // Error -> network status failed if got 400, 401 status code
    class Loading<T> : ApiState<T>() // if not get response yet from server , as default will set status "Loading"
}
```

*View Model*
```java
fun getTopHeadline() {
        viewModelScope.launch {
            getTopHeadlineUseCase().collect { result -> // use reactive programming with Flow Kotlin
                when (result) {
                    is ApiState.Success -> { data ->
                        ....
                        ....
                    }

                    is ApiState.Error<*> -> {
                        Log.d("TAG", result.message.toString())
                    }

                    else -> {
                        Log.d("TAG", "Something wrong happen")
                    }
                }

            }
        }
    }
```




## Library reference resources:
Hilt: https://developer.android.com/training/dependency-injection/hilt-android

*MVVM Architecture* : https://developer.android.com/jetpack/guide

*Coroutines* : https://developer.android.com/kotlin/coroutines

*Data Binding* : https://developer.android.com/topic/libraries/data-binding

*View Binding* : https://developer.android.com/topic/libraries/view-binding

*Leak Canary* : https://square.github.io/leakcanary/

*Glide: https* : https//github.com/bumptech/glide

*Retrofit* : https://square.github.io/retrofit/


