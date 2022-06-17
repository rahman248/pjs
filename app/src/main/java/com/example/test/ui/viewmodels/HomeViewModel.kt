package com.example.test.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.repo.UsersRepository
import com.example.test.data.repo.model.JobsDetailResponse
import com.example.test.data.repo.model.JobsResponse
import com.example.test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {



    private var _jobsData = MutableLiveData<Resource<JobsResponse>>()
    val jobsData: LiveData<Resource<JobsResponse>> = _jobsData

    private var _jobsDetailData = MutableLiveData<Resource<JobsDetailResponse>>()
    val jobsDetailData: LiveData<Resource<JobsDetailResponse>> = _jobsDetailData

    fun getAllJobsType() {
        viewModelScope.launch {
            _jobsData.postValue(Resource.loading(null))
            try {
                val response = usersRepository.jobList().await()
                if (response.responseStatus == "1") {
                    _jobsData.postValue(Resource.success(response))
                } else {
                    _jobsData.postValue(Resource.error(null, response.message))
                }
            } catch (ex: Exception) {
                _jobsData.postValue(Resource.error(null, ex.message.toString()))
            }
        }
    }

    fun viewJobsDetail(jotId: String) {
        viewModelScope.launch {
            _jobsDetailData.postValue(Resource.loading(null))
            try {
                val response = usersRepository.jobById(jotId).await()
                if(response.responseStatus == "1"){
                    _jobsDetailData.postValue(Resource.success(response))
                } else {
                    _jobsDetailData.postValue(Resource.error(null, response.message))
                }
            }
            catch (ex: Exception){
                _jobsDetailData.postValue(Resource.error(null, ex.message.toString()))
            }
        }
    }

    fun lockJobsDetail(jotId: String) {
        print("DATA  $jotId")
        viewModelScope.launch {
            _jobsDetailData.postValue(Resource.loading(null))
            try {
                val response = usersRepository.lockJobByIdAsync(jotId).await()
                if(response.responseStatus == "1"){
                    _jobsDetailData.postValue(Resource.success(response))
                } else {
                    _jobsDetailData.postValue(Resource.error(null, response.message))
                }
            }
            catch (ex: Exception){
                _jobsDetailData.postValue(Resource.error(null, ex.message.toString()))
            }
        }
    }

    fun unlockJobsDetail(jotId: String) {
        viewModelScope.launch {
            _jobsDetailData.postValue(Resource.loading(null))
            try {
                val response = usersRepository.unlockJobByIdAsync(jotId).await()
                if(response.responseStatus == "1"){
                    _jobsDetailData.postValue(Resource.success(response))
                } else {
                    _jobsDetailData.postValue(Resource.error(null, response.message))
                }
            }
            catch (ex: Exception){
                _jobsDetailData.postValue(Resource.error(null, ex.message.toString()))
            }
        }
    }

    fun editJobName(s: String, id: String) {
        viewModelScope.launch {
            _jobsDetailData.postValue(Resource.loading(null))
            try {
                val response = usersRepository.editJobsNameAsync(s, id).await()
                if(response.responseStatus == "1"){
                    _jobsDetailData.postValue(Resource.success(response))
                } else {
                    _jobsDetailData.postValue(Resource.error(null, response.message))
                }
            }
            catch (ex: Exception){
                _jobsDetailData.postValue(Resource.error(null, ex.message.toString()))
            }
        }
    }

    fun addNewjob(s: String) {

        viewModelScope.launch {
            _jobsDetailData.postValue(Resource.loading(null))
            try {
                val response = usersRepository.addNewJob(s).await()
                if(response.responseStatus == "1"){
                    _jobsDetailData.postValue(Resource.success(response))
                } else {
                    _jobsDetailData.postValue(Resource.error(null, response.message))
                }
            }
            catch (ex: Exception){
                _jobsDetailData.postValue(Resource.error(null, ex.message.toString()))
            }
        }

    }
}