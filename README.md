# BreadTask

## Android Project for getting a paginated offline first list of posts from web API

##### For getting posts use endpoint https://gorest.co.in/public/v2/posts
##### For getting comments on posts use endpoint https://gorest.co.in/public/v2/posts/{postId}/comments

## Screenshots

<img src="https://imgur.com/a8uDDy1.png" width="200">  <img src="https://imgur.com/UPQcOo7.png" width="200">     <img src="https://imgur.com/C85zm0l.png" width="200">


## Libraries 

- Jetpack compose -> for developing the app UI
- ComposeDestination -> for navigating between composables
- Paging3 -> for paginated the data that is fetched from API and from the database
- Room -> for storing data locally 
- hilt -> for dependency injection
- Coroutines -> asynchronous operations
- KotlinFlow -> for manipulating the streams of data
- Retrofit -> fetching the data from API
