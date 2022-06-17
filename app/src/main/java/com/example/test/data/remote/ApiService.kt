package com.example.test.data.remote

import com.example.test.data.repo.model.JobsDetailResponse
import com.example.test.data.repo.model.JobsResponse
import com.example.test.data.repo.model.UserResponse
import com.example.test.data.repo.model.request.JobRequest
import com.example.test.data.repo.model.request.LoginRequest
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("auth/login")
    fun fetchLogin(@Body requestBody: LoginRequest): Call<UserResponse>

    @GET("job-types")
    fun fetchJobsType(): Call<JobsResponse>

    @GET("job-types/{id}")
    fun fetchJobsById(@Path("id") id: String): Call<JobsDetailResponse>

    @PATCH("job-types/{id}/lock")
    fun fetchLockJobsById(@Path("id") id: String): Call<JobsDetailResponse>

    @PATCH("job-types/{id}/unlock")
    fun fetchULockJobsById(@Path("id") id: String): Call<JobsDetailResponse>

    @PUT("job-types/{id}")
    fun fetchPutJobsName(@Path("id") id: String, @Body jobRequest: JobRequest) : Call<JobsDetailResponse>

    @POST("job-types}")
    fun fetchAddJobsName(@Body jobRequest: JobRequest): Call<JobsDetailResponse>

}