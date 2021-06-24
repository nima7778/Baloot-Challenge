package ir.nima.balootchallenge.data.api.service

import ir.nima.balootchallenge.data.api.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q") q: String,
        @Query("from") from : String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<ResponseModel>

    @GET("/v2/everything")
    suspend fun getNews(
        @Query("domains") domain: String = "wsj.com",
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<ResponseModel>
}