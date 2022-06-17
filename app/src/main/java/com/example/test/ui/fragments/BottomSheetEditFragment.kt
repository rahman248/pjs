package com.example.test.ui.fragments


import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.test.R
import com.example.test.data.repo.model.response.JobMdl
import com.example.test.databinding.BottomSheetEditBinding
import com.example.test.utils.NoticeListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetEditFragment : BottomSheetDialogFragment() {
    var model: JobMdl? = null
    var state : String = ""
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    var mListener: NoticeListener? = null
    private lateinit var binding: BottomSheetEditBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            state = bundle.getString("state").toString()
            model = bundle.getParcelable("key")
        }

        binding.userInput.hint = if (state == "put") requireContext().getString(R.string.what_could_be_better) else "Type Jobs Name"
        binding.userInput.imeOptions = EditorInfo.IME_ACTION_DONE
        binding.userInput.setRawInputType(InputType.TYPE_CLASS_TEXT)
        var jobsId =""

        model?.let {mJobsData ->

            binding.cirLoginButton.text = "Submit"
            if (state == "put"){
                binding.labeled.text = mJobsData.jotName
            }  else {
                binding.labeled.text = "Please type new Jobs"
            }

            binding.userInput.setOnEditorActionListener { _, i, _ ->
                if (i == EditorInfo.IME_ACTION_DONE) {
                    val nameJob  = binding.userInput.text.toString()
                    nameJob.apply {
                        when {
                            this.isEmpty() || this.isBlank() -> {
                                dismiss()
                            }
                            else -> {
                                when (state) {
                                    "add" -> {
                                        dismiss()
                                        mListener?.onDialogAddItemClick(this)

                                    }
                                    else -> {
                                        dismiss()
                                        mListener?.onDialogEditPositiveClick(this, mJobsData.jotID)
                                    }
                                }

                            }
                        }
                    }

                }
                false
            }

            binding.cirLoginButton.setOnClickListener{
                val nameJob  = binding.userInput.text.toString()
                nameJob.let {
                    if (it.isEmpty() || it.isBlank()){
                        dismiss()
                    } else{
                        if (state == "put"){
                            mListener?.onDialogEditPositiveClick(it, mJobsData.jotID)
                        } else {
                            mListener?.onDialogAddItemClick(it)
                        }

                    }
                }
                dismiss()
            }
        }
    }


}