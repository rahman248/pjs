package com.example.test.data.repo.model

import com.example.test.base.BaseResponse
import com.example.test.data.repo.model.response.UserMdl
import com.google.gson.annotations.SerializedName

class UserResponse : BaseResponse()  {
    @SerializedName("data")
    lateinit var `data`: UserMdl
}