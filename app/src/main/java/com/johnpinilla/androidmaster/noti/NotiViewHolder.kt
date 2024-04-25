package com.johnpinilla.androidmaster.noti


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.johnpinilla.androidmaster.databinding.ItemNotiviewsBinding
import com.squareup.picasso.Picasso

class NotiViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private  val binding = ItemNotiviewsBinding.bind(view)

    fun bind(NotiItemResponse: notiItemResponse, onItemSelected: (String) -> Unit){
        binding.tvNotiName.text = NotiItemResponse.title
        binding.tvNotiDescription.text = NotiItemResponse.description
        if(NotiItemResponse.urlToImage != null){
            Picasso.get().load(NotiItemResponse.urlToImage).into(binding.ivNoti)
        }else{
            Picasso.get().load("https://png.pngtree.com/png-vector/20210520/ourlarge/pngtree-image-file-icon-that-is-damaged-or-corrupted-png-image_3296451.png").into(binding.ivNoti)
        }

        //binding.root.setOnClickListener{onItemSelected(NotiItemResponse.notiId)}
    }
}