package com.android.energiterbarukan.helper

import androidx.recyclerview.widget.DiffUtil
import com.android.energiterbarukan.database.DataEnergi

class NoteDiffCallback(
    private val oldDataList: List<DataEnergi>,
    private val newDataList: List<DataEnergi>): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldDataList.size

    override fun getNewListSize(): Int = newDataList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDataList[oldItemPosition].id == newDataList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = oldDataList[oldItemPosition]
        val newData = newDataList[newItemPosition]
        return oldData.title == newData.title && oldData.description == newData.description
    }
}