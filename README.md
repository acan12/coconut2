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

### gradle script
Add in your *gradle.properties*
```sh
authToken=jp_kupq41fvlrn3tcir2aggml3ck9
```


Put it in your *settings.gradle.kts* at the end of repositories:
```sh
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
      ...
      maven {
          url = uri("https://jitpack.io")
          credentials.username = providers.gradleProperty("authToken").get()
      }
  }
}
```
Add dependency
```sh
dependencies {
    implementation("com.github.acan12:coconut2:<RELEASE-TAG-VERSION>")
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
        SampleDatabase::class.java,
        BuildConfig.DB_NAME,
    ).build()

    @Provides
    @Singleton
    fun provideApi(): Api = Api(ApiManager())

    @Provides
    @Singleton
    fun provideConnectionChecker(@ApplicationContext context: Context): ConnectionManager =
        ConnectionManager(context)
}
```

#### 2. Custom Retrofit Interception

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
            apiDomain = BuildConfig.SERVER_URL,
            allowUntrusted = true,
            apiService = ApiService::class.java,
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
    data class Success<out T>(val data: T) : ApiState<T>()
    data class Error<S>(val message: String?, val data: S? = null): ApiState<Nothing>()
    class Loading<T> : ApiState<T>()
}
```

*View Model*
```java
fun getTopHeadline() {
        viewModelScope.launch {
            getTopHeadlineUseCase().collect { result ->
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


