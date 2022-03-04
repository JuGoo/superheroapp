# superheroapp

## Libraries and tools

- Kotlin
- AndroidX
    - App Compat
    - RecyclerView
- Android Architecture Components
    - Lifecycle and ViewModel
- Flow
- Retrofit
- GSon  
- Kotlin Coroutines
- Glide

## Project Structure

This project is built using Clean Architecture and is structured in the following way:

**app** - contains Activities/Fragments

**presentation** - contains ViewModels for the presentation layer

**domain** - contains entities and use cases for the presentation layer to access data from the **data** layer

**data** -  contains data models and repositories for getting data

## TODO

- Unit tests
- Code Review