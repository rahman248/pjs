package com.example.test.utils

import com.example.test.data.repo.model.response.JobMdl
import com.example.test.ui.fragments.UnlockDialogFragment

interface NoticeListener {
    fun onDialogPositiveClick(mJobMdl: JobMdl )
    fun onDialogEditPositiveClick(s: String, id: String)
    fun onDialogAddItemClick(s: String)
}