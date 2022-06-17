package com.example.test.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.test.R
import com.example.test.data.repo.model.response.JobMdl
import com.example.test.databinding.BottomSheetLockBinding
import com.example.test.utils.NoticeListener


class UnlockDialogFragment() : DialogFragment() {
    var model: JobMdl? = null

    var mListener: NoticeListener? = null
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private lateinit var binding: BottomSheetLockBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetLockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            model = bundle.getParcelable("key")
        }




        model?.let {jobModels ->
            binding.labeled.text = jobModels.jotName
            val status = if (jobModels.jotActive){
                "Lock"
            } else {
                "Unlock"
            }

            binding.tvStatusLbl.text = "Are you sure to $status this ${ jobModels.jotName}"

            binding.cirLoginButton.setOnClickListener{
                mListener?.onDialogPositiveClick(jobModels)
                dismiss()

            }

            binding.cirCancelButton.setOnClickListener{
                dismiss()
            }
        }


    }


}