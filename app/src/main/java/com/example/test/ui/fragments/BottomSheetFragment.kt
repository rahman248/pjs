package com.example.test.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.R
import com.example.test.data.repo.model.response.JobMdl
import com.example.test.databinding.BottomSheetModalBinding
import com.example.test.utils.ConvertDateTimeFormat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {
    var model: JobMdl? = null
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private lateinit var binding: BottomSheetModalBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetModalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            model = bundle.getParcelable("key")
        }


        model?.let {
            binding.labeled.text = it.jotName
            binding.tvStatus.text = it.jotActiveLabel
            binding.tvCreate.text = ConvertDateTimeFormat(it.jotCreatedAt)
            binding.tvUpdate.text = ConvertDateTimeFormat(it.jotUpdatedAt)
        }
        
        binding.cirLoginButton.setOnClickListener{
            dismiss()
        }




    }
}