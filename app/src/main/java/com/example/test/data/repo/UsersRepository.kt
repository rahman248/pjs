package com.example.test.data.repo

import com.example.test.data.repo.model.JobsDetailResponse
import com.example.test.data.repo.model.JobsResponse
import com.example.test.data.repo.model.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call

interface UsersRepository {
    suspend fun login(username: String, password: String) : Deferred<UserResponse>
    suspend fun jobList(): Deferred<JobsResponse>
    suspend fun jobById(jotID: String): Deferred<JobsDetailResponse>
    suspend fun lockJobByIdAsync(jotId: String): Deferred<JobsDetailResponse>
    suspend fun unlockJobByIdAsync(jotId: String): Deferred<JobsDetailResponse>
    suspend fun editJobsNameAsync(name: String, id : String): Deferred<JobsDetailResponse>
    suspend fun addNewJob(s: String): Deferred<JobsDetailResponse>
}