package com.example.test.data.repo.model

import com.example.test.base.BaseResponse
import com.example.test.data.repo.model.response.JobMdl
import com.example.test.data.repo.model.response.UserMdl
import com.google.gson.annotations.SerializedName

class JobsResponse : BaseResponse() {
    @SerializedName("data")
    lateinit var `data`: List<JobMdl>
}

