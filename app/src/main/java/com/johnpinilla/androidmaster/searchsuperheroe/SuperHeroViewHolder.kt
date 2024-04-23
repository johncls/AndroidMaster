package com.johnpinilla.androidmaster.searchsuperheroe
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.johnpinilla.androidmaster.databinding.ItemSuperheroeBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view:View): RecyclerView.ViewHolder(view) {
    private  val binding = ItemSuperheroeBinding.bind(view)

    fun bind(superheroItemResponse: SuperheroItemResponse, onItemSelected: (String) -> Unit){
        binding.tvSuperHeroName.text = superheroItemResponse.name
        Picasso.get().load(superheroItemResponse.superheroImage.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener{onItemSelected(superheroItemResponse.superheroId)}
    }
}