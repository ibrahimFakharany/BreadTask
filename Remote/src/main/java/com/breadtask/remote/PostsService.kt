package com.breadtask.remote

import com.breadtask.model.Comment
import com.breadtask.model.Post
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsService {
    @GET("posts")
    suspend fun getPosts(
        @Query("page") page: Int? = 1, @Query("per_page") perPage: Int? = 100
    ): List<Post>

    @GET("posts/{id}/comments")
    suspend fun getCommentsForPost(
        @Path("id") postId: Int,
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = 100
    ): List<Comment>
}