package com.topibatu.sibarong.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.topibatu.sibarong.database.entity.HistoryEntity
import com.topibatu.sibarong.databinding.ItemViewBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder>() {

    private val listNews = ArrayList<HistoryEntity>()

    fun setData(data: List<HistoryEntity>){
        listNews.clear()
        listNews.addAll(data)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(historyEntity: HistoryEntity) {
            with(binding){
                tvItemTitle.text = historyEntity.text
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ListViewHolder {
       val itemViewBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ListViewHolder, position: Int) {
        holder.bind(listNews[position])
    }

    override fun getItemCount(): Int  = listNews.size
}