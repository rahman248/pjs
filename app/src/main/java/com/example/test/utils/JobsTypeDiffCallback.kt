package com.example.test.utils

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.test.data.repo.model.response.JobMdl


class JobsTypeDiffCallback : DiffUtil.Callback() {

    private var mOldList: List<JobMdl>? = null
    private var mNewList: List<JobMdl>? = null

    fun JobsTypeDiffCallback(oldList: List<JobMdl>?, newList: List<JobMdl>?) {
        mOldList = oldList
        mNewList = newList
    }
    /**
     * Returns the size of the old list.
     *
     * @return The size of the old list.
     */
    override fun getOldListSize(): Int  = if (mOldList != null) mOldList!!.size else 0

    /**
     * Returns the size of the new list.
     *
     * @return The size of the new list.
     */
    override fun getNewListSize(): Int  =  if (mNewList != null) mNewList!!.size else 0

    /**
     * Called by the DiffUtil to decide whether two object represent the same Item.
     *
     *
     * For example, if your items have unique ids, this method should check their id equality.
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return True if the two items represent the same object or false if they are different.
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mNewList?.get(newItemPosition)?.jotID == mOldList?.get(oldItemPosition)?.jotID

    }

    /**
     * Called by the DiffUtil when it wants to check whether two items have the same data.
     * DiffUtil uses this information to detect if the contents of an item has changed.
     *
     *
     * DiffUtil uses this method to check equality instead of [Object.equals]
     * so that you can change its behavior depending on your UI.
     * For example, if you are using DiffUtil with a
     * [RecyclerView.Adapter], you should
     * return whether the items' visual representations are the same.
     *
     *
     * This method is called only if [.areItemsTheSame] returns
     * `true` for these items.
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list which replaces the
     * oldItem
     * @return True if the contents of the items are the same or false if they are different.
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return mNewList?.get(newItemPosition) == mOldList?.get(oldItemPosition)
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val newJobsType: JobMdl = mNewList!![newItemPosition]
        val oldJobsType: JobMdl = mOldList!![oldItemPosition]
        val diffBundle = Bundle()
        if (newJobsType.jotActive != oldJobsType.jotActive) {
            diffBundle.putBoolean("jotActive", newJobsType.jotActive)
        }
        if (newJobsType.jotName !== oldJobsType.jotName) {
            diffBundle.putString("name", newJobsType.jotName)
        }
        if (newJobsType.jotActiveLabel !== oldJobsType.jotActiveLabel) {
            diffBundle.putString("actLabel", newJobsType.jotActiveLabel)
        }
        return if (diffBundle.size() == 0) null else diffBundle
    }
}

