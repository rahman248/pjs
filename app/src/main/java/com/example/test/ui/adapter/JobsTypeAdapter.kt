package com.example.test.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.BR
import com.example.test.R
import com.example.test.data.repo.model.response.JobMdl
import com.example.test.databinding.ItemJobLayoutBinding
import com.example.test.ui.fragments.HomeFragment
import com.example.test.utils.RecycleViewClickListener
import java.lang.ref.WeakReference

class JobsTypeAdapter(fragment: HomeFragment) : RecyclerView.Adapter<JobsTypeAdapter.JobTypeViewHolder>() {

    var listener: RecycleViewClickListener? = null
    private var listJobTypes = mutableListOf<JobMdl>()
    private val fragment = WeakReference(fragment)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobTypeViewHolder {
        val binding = ItemJobLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobTypeViewHolder(binding, fragment)
    }

    override fun getItemCount(): Int = listJobTypes.size

    override fun onBindViewHolder(holder: JobTypeViewHolder, position: Int){

        holder.bind(listJobTypes[position], position)
    }

    fun setData(datas: List<JobMdl>){
        listJobTypes.clear()
        listJobTypes.addAll(datas)
        notifyDataSetChanged()
    }

    inner class JobTypeViewHolder(private val binding: ItemJobLayoutBinding,
                                  private val fragment: WeakReference<HomeFragment>
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: JobMdl, position: Int){
            binding.setVariable(BR.item, data)
            binding.setVariable(BR.view, fragment.get())

            when {
                position %2 == 1 -> {
                    binding.itemContainer.setBackgroundColor(Color.WHITE);
                    binding.jobsStatus.setTextColor(Color.BLACK)
                    binding.jobsTitle.setTextColor(Color.BLACK)
                    binding.imgView.setImageResource(R.drawable.ic_view_gray)
                    binding.imgEdit.setImageResource(R.drawable.ic_pen)
                }
                else -> {
                    binding.itemContainer.setBackgroundColor(Color.parseColor("#D2DDD9"));
                    binding.jobsStatus.setTextColor(Color.WHITE)
                    binding.jobsTitle.setTextColor(Color.WHITE)
                    binding.imgView.setImageResource(R.drawable.ic_view)
                    binding.imgEdit.setImageResource(R.drawable.ic_pen_white)
                }
            }

            if (data.jotActive){
                binding.imgDel.setImageResource(R.drawable.ic_unlock)
            } else {
                binding.imgDel.setImageResource(R.drawable.ic_lock)
            }

            binding.imgView.setOnClickListener{
                listener?.onViewSelected(listJobTypes[position])
                notifyItemChanged(absoluteAdapterPosition);
            }
            binding.imgEdit.setOnClickListener{
                listener?.onEditSelected(listJobTypes[position])
                notifyItemChanged(absoluteAdapterPosition);
            }
            binding.imgDel.setOnClickListener{
                listener?.onLockUnlockSelected(listJobTypes[position])
                notifyItemChanged(absoluteAdapterPosition);

            }
            binding.executePendingBindings()

        }
    }







}