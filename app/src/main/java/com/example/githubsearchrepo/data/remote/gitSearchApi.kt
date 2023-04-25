package com.example.githubsearchrepo.data.remote

import com.example.githubsearchrepo.data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface gitSearchApi {

    @GET("/search/repositories?q={query}&type=Repositories")
    suspend fun searchRepo(@Query("query") query: String):SearchDto

}