package com.johnpinilla.androidmaster.searchsuperheroe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.StructuredName
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import com.johnpinilla.androidmaster.R
import com.johnpinilla.androidmaster.databinding.ActivityDetailSuperHereoBinding
import com.johnpinilla.androidmaster.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.roundToInt

class DetailSuperHereoActivity : AppCompatActivity() {
    companion object{
        const val EXRTRA_ID = "extra_id"
    }

    private lateinit var  binding: ActivityDetailSuperHereoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHereoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXRTRA_ID).orEmpty()
        getSuperHeroInformations(id)
    }

    private fun getSuperHeroInformations(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail = getRetroFit().create(ApiService::class.java).getSuperheroDetail(id)

            if(superHeroDetail.body() != null){
                runOnUiThread{
                    createUI(superHeroDetail.body()!!)
                }

            }
        }

    }

    /**
     * Metodo para pintar la vista con la api
     */
    private fun createUI(superHero:SuperHeroDetailResponse){
            Picasso.get().load(superHero.image.url).into(binding.ivSuperhero)
            binding.tvSuperheroName.text = superHero.name
            prepareStats(superHero.powerstats)
            binding.tvSuperheroRealName.text = superHero.biography.fullName
            binding.tvPublisher.text = superHero.biography.publisher
    }

    /**
     * Metodo que toma los datos del heroe
     */
    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewPower, powerstats.power)
    }

    /**
     * Actualiza los valores de cada barra
     */
    private fun updateHeight(view: View, stat:String){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    /**
     * Devuelve una unidad de pixeles en dp
     */
    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    /**
     * Metodo que llama el retrofti
     */
    private fun getRetroFit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}