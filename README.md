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
Add in your gradle.properties 
```sh
authToken=jp_kupq41fvlrn3tcir2aggml3ck9
```


Put it in your settings.gradle.kts at the end of repositories:
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



