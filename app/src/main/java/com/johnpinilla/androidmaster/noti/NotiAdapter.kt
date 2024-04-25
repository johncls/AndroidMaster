package com.johnpinilla.androidmaster.noti

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johnpinilla.androidmaster.R


class NotiAdapter (
    var notiViewList: List<notiItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<NotiViewHolder>() {

    fun updateList(list: List<notiItemResponse>) {
        notiViewList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotiViewHolder {
        return NotiViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notiviews, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotiViewHolder, position: Int) {
        holder.bind(notiViewList[position], onItemSelected)

    }

    override fun getItemCount() = notiViewList.size

}