package com.example.test.data.repo.model

import com.example.test.base.BaseResponse
import com.example.test.data.repo.model.response.JobMdl
import com.google.gson.annotations.SerializedName

class JobsDetailResponse : BaseResponse() {
    @SerializedName("data")
    lateinit var `data`: JobMdl
}