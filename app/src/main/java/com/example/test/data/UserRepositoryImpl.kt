package com.example.test.data

import android.os.Build
import androidx.lifecycle.viewModelScope
import com.example.test.base.BaseRepository
import com.example.test.data.remote.ApiService
import com.example.test.data.remote.source.AuthRemoteDataSource
import com.example.test.data.repo.UsersRepository
import com.example.test.data.repo.model.JobsDetailResponse
import com.example.test.data.repo.model.JobsResponse
import com.example.test.data.repo.model.UserResponse
import com.example.test.data.repo.model.request.JobRequest
import com.example.test.data.repo.model.request.LoginRequest
import com.example.test.utils.ContextProviders
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class  UserRepositoryImpl @Inject constructor(
    private val contextProviders: ContextProviders,
    private val remoteDataSource: AuthRemoteDataSource,
) : BaseRepository(), UsersRepository {



    private fun createRequest(username: String, password: String) = LoginRequest(
        username,
        password
    )

    private fun createPutRequest(name: String) = JobRequest(
        name
    )


    override suspend fun login(username: String, password: String): Deferred<UserResponse> {
        return CoroutineScope(Dispatchers.IO).async{
            lateinit var response: UserResponse
            try {
                response = execute(remoteDataSource.login(createRequest(username, password)))
                response.responseStatus = "1"
            }
            catch (ex: Exception){
                response = UserResponse()
                response.responseStatus = "-1"
                response.message = ex.message.toString()
            }
            response
        }
    }

    override suspend fun jobList(): Deferred<JobsResponse> {
        return CoroutineScope(Dispatchers.IO).async{
            lateinit var response: JobsResponse
            try {
                response = execute(remoteDataSource.jobTypes())
                response.responseStatus = "1"
            }
            catch (ex: Exception){
                response = JobsResponse()
                response.responseStatus = "-1"
                response.message = ex.message.toString()
            }
            response
        }
    }

    override suspend fun jobById(jotID: String): Deferred<JobsDetailResponse> {
        return CoroutineScope(Dispatchers.IO).async {
            lateinit var response: JobsDetailResponse
            try {
                response = execute(remoteDataSource.jobsDetailById(jotID))
                response.responseStatus = "1"
            }
            catch (ex: Exception){
                response = JobsDetailResponse()
                response.responseStatus = "-1"
                response.message = ex.message.toString()
            }
            response
        }
    }

    override suspend fun lockJobByIdAsync(jotID: String): Deferred<JobsDetailResponse> {
        return CoroutineScope(Dispatchers.IO).async {
            lateinit var response: JobsDetailResponse
            try {
                response = execute(remoteDataSource.lockJobsDetailById(jotID))
                response.responseStatus = "1"
            }
            catch (ex: Exception){
                response = JobsDetailResponse()
                response.responseStatus = "-1"
                response.message = ex.message.toString()
            }
            response
        }
    }

    override suspend fun unlockJobByIdAsync(jotID: String): Deferred<JobsDetailResponse> {
        return CoroutineScope(Dispatchers.IO).async {
            lateinit var response: JobsDetailResponse
            try {
                response = execute(remoteDataSource.unlockJobsDetailById(jotID))
                response.responseStatus = "1"
            }
            catch (ex: Exception){
                response = JobsDetailResponse()
                response.responseStatus = "-1"
                response.message = ex.message.toString()
            }
            response
        }
    }

    override suspend fun editJobsNameAsync(name: String, id : String): Deferred<JobsDetailResponse> {
        return CoroutineScope(Dispatchers.IO).async{
            lateinit var response: JobsDetailResponse
            try {
                response = execute(remoteDataSource.putJobsName(createPutRequest(name), id))
                response.responseStatus = "1"
            }
            catch (ex: Exception){
                response = JobsDetailResponse()
                response.responseStatus = "-1"
                response.message = ex.message.toString()
            }
            response
        }
    }

    override suspend fun addNewJob(s: String): Deferred<JobsDetailResponse> {
        return CoroutineScope(Dispatchers.IO).async{
            lateinit var response: JobsDetailResponse
            try {
                response = execute(remoteDataSource.addJobsName(createPutRequest(s)))
                response.responseStatus = "1"
            }
            catch (ex: Exception){
                response = JobsDetailResponse()
                response.responseStatus = "-1"
                response.message = ex.message.toString()
            }
            response
        }
    }


}