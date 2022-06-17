package com.example.test.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.UserRepositoryImpl
import com.example.test.data.repo.UsersRepository
import com.example.test.data.repo.model.UserResponse
import com.example.test.data.repo.model.request.LoginRequest
import com.example.test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel(){

    private var _users = MutableLiveData<Resource<UserResponse>>()
    val users: LiveData<Resource<UserResponse>> = _users
    fun fetchLoginApi(username: String, password: String){
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            try {
                val response = usersRepository.login(username, password).await()
                if(response.responseStatus == "1"){
                    _users.postValue(Resource.success(response))
                } else {
                    _users.postValue(Resource.error(null, response.message))
                }
            }
            catch (ex: Exception){
                _users.postValue(Resource.error(null, ex.message.toString()))
            }
        }
    }
}
