package com.example.homework.network.user_service

import com.example.homework.network.user_service.GitUser
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query


interface GitUserApiService {
    @GET("users")
    suspend fun getGitUserList(
        @Query("accept") contentType: String? = "application/vnd.github.v3+json",
        @Query("since") since: String? = null,
        @Query("per_page") itemsPerPage: Int? = null,
    ): List<GitUser>

    @PATCH("user")
    suspend fun getGitUser(): List<GitUser>
}
