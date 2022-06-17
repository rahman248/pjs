package com.example.test.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.data.repo.model.JobsResponse
import com.example.test.data.repo.model.response.JobMdl
import com.example.test.databinding.FragmentHomeBinding
import com.example.test.ui.adapter.JobsTypeAdapter
import com.example.test.ui.viewmodels.HomeViewModel
import com.example.test.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment(
    viewModelTestFragment: HomeViewModel? = null
) : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home,
    HomeViewModel::class.java,
    viewModelTestFragment
), RecycleViewClickListener, NoticeListener {
    private val progressDialog by lazy { CustomProgressDialog(requireActivity()) }
    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
    private var mJobType: JobMdl? = null
    private lateinit var jobsAdapter: JobsTypeAdapter
    private var mHandler : Handler? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllJobsType()

        fectJobs()
        mHandler = Handler(Looper.getMainLooper())



        initRecycleView()
        initClickListener()

    }

    private fun dismisDialog()  = mHandler?.apply {
        this.postDelayed({
            // Dismiss progress bar after 4 seconds
            progressDialog.stop()
            jobsAdapter.notifyDataSetChanged()
        }, 4000)
    }


    private fun fectJobs() {
        progressDialog.start("Please Wait...")
        viewModel.jobsData.observe(viewLifecycleOwner) { retVal ->
            when (retVal.status) {
                EnumStatus.SUCCESS -> {
                    if (retVal.data == null) {

                        return@observe
                    } else {
                        fetchJobCollection(retVal.data)
                    }
                }
                EnumStatus.LOADING -> {  dismisDialog()}
                EnumStatus.ERROR -> {}
            }

        }
    }

    private fun initClickListener() {
        binding.fab.setOnClickListener{
            val bundle = Bundle()
            val fragment = BottomSheetEditFragment()
            bundle.putString("state", "add")
            bundle.putParcelable("key", null)
            fragment.arguments = bundle
            fragment.show(requireActivity().supportFragmentManager, "BottomSheetEditFragment")
            fragment.mListener = this
        }
    }

    private fun fetchJobCollection(datas: JobsResponse?) {
        datas?.let {
            jobsAdapter.setData(it.data)
            jobsAdapter.notifyDataSetChanged()


        }
    }


    private fun initRecycleView() {
        jobsAdapter = JobsTypeAdapter(this)
        val itemDecorator = RecyclerViewItemDecoration(requireContext(), R.drawable.devider)
        binding.rvJobs.apply {
            adapter = jobsAdapter
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            addItemDecoration(itemDecorator)
        }
        jobsAdapter.listener = this

    }

    override fun onViewSelected(mJobMdl: JobMdl) {
        progressDialog.start("Please Wait...")
        viewModel.viewJobsDetail(mJobMdl.jotID)
        openBottomSheetDialog()
    }

    override fun onLockUnlockSelected(mJobMdl: JobMdl) {

        val bundle = Bundle()
        val fragment = UnlockDialogFragment()
        bundle.putParcelable("key", mJobMdl)// use as per your need
        fragment.arguments = bundle
        fragment.show(requireActivity().supportFragmentManager, "UnlockDialogFragment")
        mJobType = mJobMdl
        fragment.mListener = this

    }

    override fun onEditSelected(mJobMdl: JobMdl) {
        val bundle = Bundle()
        val fragment = BottomSheetEditFragment()
        bundle.putString("state", "put")
        bundle.putParcelable("key", mJobMdl)// use as per your need
        fragment.arguments = bundle
        fragment.show(requireActivity().supportFragmentManager, "BottomSheetEditFragment")
        mJobType = mJobMdl
        fragment.mListener = this
    }

    private fun openBottomSheetDialog() {
        viewModel.jobsDetailData.observe(viewLifecycleOwner) { retVal ->
            when (retVal.status) {
                EnumStatus.SUCCESS -> {
                    if (retVal.data == null) {

                        return@observe
                    } else {
                        val bundle = Bundle()
                        val fragment = BottomSheetFragment()
                        bundle.putParcelable("key", retVal.data.data)// use as per your need
                        fragment.arguments = bundle
                        fragment.show(
                            requireActivity().supportFragmentManager,
                            "BottomSheetFragment"
                        )

                    }
                }
                EnumStatus.LOADING -> {  dismisDialog()}
                EnumStatus.ERROR -> {  dismisDialog()}
            }

        }

    }

    override fun onDialogPositiveClick(mJobMdl: JobMdl) {
        when {
            mJobMdl.jotActive -> {
                progressDialog.start("Please Wait...")
                viewModel.lockJobsDetail(mJobMdl.jotID)
                viewModel.getAllJobsType()
                jobsAdapter.notifyDataSetChanged()

            }
            else -> {
                progressDialog.start("Please Wait...")
                viewModel.unlockJobsDetail(mJobMdl.jotID)
                viewModel.getAllJobsType()
                jobsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDialogEditPositiveClick(s: String, id: String) {
        progressDialog.start("Please Wait...")
        viewModel.editJobName(s, id)
        viewModel.getAllJobsType()
        jobsAdapter.notifyDataSetChanged()
    }

    override fun onDialogAddItemClick(s: String) {
        progressDialog.start("Please Wait...")
        viewModel.addNewjob(s)
        viewModel.getAllJobsType()
        jobsAdapter.notifyDataSetChanged()
    }
}
