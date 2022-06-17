package com.example.test.base

import android.util.Log
import com.example.test.BuildConfig
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.HttpException

abstract class BaseRepository {
    companion object {

        private const val TAG = "BaseRepository"

        fun<T : BaseResponse> execute(call : Call<T>) : T {
            try{
                val response = call.execute()
                return when(response.isSuccessful){
                    true -> {
                        if(BuildConfig.BUILD_TYPE == ("debug"))
                            Log.d(TAG, Gson().toJson(response.body()!!))
                        response.body()!!
                    }
                    false -> {
                        if(BuildConfig.BUILD_TYPE == "debug")
                            Log.d(TAG, response.message())
                        throw HttpException(response)
                    }
                }
            }
            catch (e : Exception){
                if(BuildConfig.BUILD_TYPE == "debug")
                    e.message?.let {
                        Log.d(TAG, it)
                    }
                throw e
            }
        }
    }
}