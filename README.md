# BreadTask

## Android Project for getting a paginated and offline-first list of posts from web API

## Description

###### The app downloads the data first from the API and then saves it in the database lastly showing it to the user To ensure an excellent user experience and ensure working in weak internet conditions

##### For getting posts use endpoint https://gorest.co.in/public/v2/posts
##### For getting comments on posts use endpoint https://gorest.co.in/public/v2/posts/{postId}/comments

## Screenshots

<img src="https://imgur.com/a8uDDy1.png" width="200">  <img src="https://imgur.com/UPQcOo7.png" width="200">     <img src="https://imgur.com/C85zm0l.png" width="200">

## Architecture
- Clean architecture
- MVI
- 
## Libraries 

- <b>Jetpack compose </b>-> for developing the app UI
- <b>ComposeDestination</b> -> for navigating between composables
- <b>Paging3</b> -> for paginated the data that is fetched from API and from the database
- <b>Room</b> -> for storing data locally 
- <b>Hilt</b> -> for dependency injection
- <b>Coroutines</b> -> asynchronous operations
- <b>KotlinFlow</b> -> for manipulating the streams of data
- <b>Retrofit</b> -> fetching the data from API
