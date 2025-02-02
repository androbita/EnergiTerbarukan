package com.android.energiterbarukan.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.energiterbarukan.database.DataEnergi
import com.android.energiterbarukan.databinding.ItemNoteBinding
import com.android.energiterbarukan.helper.NoteDiffCallback
import com.android.energiterbarukan.ui.addupdatedelete.DataEnergiAUDActivity

class DataEnergiAdapter: RecyclerView.Adapter<DataEnergiAdapter.DataEnergiViewHolder>() {

    private val listDataEnergi = ArrayList<DataEnergi>()
    fun setListDataEnergi(listDataEnergi: List<DataEnergi>){
        val diffCallback = NoteDiffCallback(this.listDataEnergi, listDataEnergi)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listDataEnergi.clear()
        this.listDataEnergi.addAll(listDataEnergi)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class DataEnergiViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(dataEnergi: DataEnergi){
            with(binding){
                tvItemTitle.text = dataEnergi.title
                tvItemDate.text = dataEnergi.date
                tvItemDescription.text = dataEnergi.description

                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, DataEnergiAUDActivity::class.java)
                    intent.putExtra(DataEnergiAUDActivity.EXTRA_DATA, dataEnergi)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataEnergiViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataEnergiViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listDataEnergi.size
    }

    override fun onBindViewHolder(holder: DataEnergiViewHolder, position: Int) {
        holder.bind(listDataEnergi[position])
    }
}