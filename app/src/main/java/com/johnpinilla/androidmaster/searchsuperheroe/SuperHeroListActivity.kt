package com.johnpinilla.androidmaster.searchsuperheroe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.johnpinilla.androidmaster.R
import com.johnpinilla.androidmaster.databinding.ActivitySuperHeroListBinding
import com.johnpinilla.androidmaster.searchsuperheroe.DetailSuperHereoActivity.Companion.EXRTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {
    private lateinit var  binding : ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetroFit()
        initUI()
    }

    /**
     * Función para iniciar la vista
     */
    private fun initUI(){
        this.searchByName("THOR");
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /**
             * Funcion para el boton buscar
             */
            override fun onQueryTextSubmit(query: String?): Boolean {
                 Log.i("john pinilla", query.toString())
                if (query != null) {

                    if(query.isEmpty()){
                        Log.i("johnpinilla", "ffdkñldf")
                        searchByName("Thor");
                    }
                }
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


        adapter = SuperHeroAdapter{superheroeId -> navigateToDetail(superheroeId)}
        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter = adapter
    }

    /**
     * Buscar super heroes consumiendo la api
     */
    private fun searchByName(query:String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> = retrofit.create(ApiService::class.java).getSuperheroes(query)
            if(myResponse.isSuccessful){
                val response: SuperHeroDataResponse ? = myResponse.body()
                if(response != null){
                    Log.i("johnpinilla",  response.toString())
                    runOnUiThread{
                        adapter.updateList(response.heroes)
                        binding.progressBar.isVisible = false;
                    }
                }

            }else{
                Log.i("johnpinilla", "no funciona")
            }
        }
    }

    /**
     *
     */
    private fun getRetroFit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id:String){
        val intent = Intent(this,DetailSuperHereoActivity::class.java)
        intent.putExtra(EXRTRA_ID,id)
        startActivity(intent)
    }


}