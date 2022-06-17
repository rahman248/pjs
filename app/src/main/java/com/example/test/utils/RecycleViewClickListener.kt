package com.example.test.utils

import android.view.View
import com.example.test.data.repo.model.response.JobMdl

interface RecycleViewClickListener {
    fun onViewSelected(mJobMdl: JobMdl)
    fun onLockUnlockSelected(mJobMdl: JobMdl)
    fun onEditSelected(mJobMdl: JobMdl)
}