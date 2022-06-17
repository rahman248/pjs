package com.example.test.data.remote.source

import com.example.test.data.remote.ApiService
import com.example.test.data.repo.model.request.JobRequest
import com.example.test.data.repo.model.request.LoginRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authenticationService: ApiService) {
    fun login(loginRequest: LoginRequest) = authenticationService.fetchLogin(loginRequest)
    fun jobTypes() = authenticationService.fetchJobsType()
    fun jobsDetailById(id : String) = authenticationService.fetchJobsById(id)
    fun lockJobsDetailById(id: String) = authenticationService.fetchLockJobsById(id)
    fun unlockJobsDetailById(id: String) = authenticationService.fetchULockJobsById(id)
    fun putJobsName(jobRequest: JobRequest, id : String)= authenticationService.fetchPutJobsName(id, jobRequest)
    fun addJobsName(addNewJob: JobRequest)= authenticationService.fetchAddJobsName(addNewJob)

}