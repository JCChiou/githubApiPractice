package com.example.githubsearchrepo.data.remote

import com.example.githubsearchrepo.data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GitSearchApi {

    @GET("/search/repositories")
    suspend fun searchRepo(
        @Query("q") query: String,
    ): SearchDto

}