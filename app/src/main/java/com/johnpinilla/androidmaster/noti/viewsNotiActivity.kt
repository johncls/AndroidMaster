package com.johnpinilla.androidmaster.noti

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.johnpinilla.androidmaster.databinding.ActivityViewsNotiBinding
import com.johnpinilla.androidmaster.searchsuperheroe.DetailSuperHereoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class viewsNotiActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityViewsNotiBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: NotiAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewsNotiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetroFit()
        initUI()
    }

    /**
     *
     */
    private fun getRetroFit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initUI(){
        this.searchByName("colombia");
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
        adapter = NotiAdapter{superheroeId -> navigateToDetail(superheroeId)}
        binding.rvNotiView.setHasFixedSize(true)
        binding.rvNotiView.layoutManager = LinearLayoutManager(this)
        binding.rvNotiView.adapter = adapter
    }

    /**
     * Buscar super heroes consumiendo la api
     */
    private fun searchByName(query:String) {
        val language = "es"
        val sortBy = "popularity"
        val apikey = "a6b5cf9d0f3b4a3c8c2cfb0afb8d5e27"
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<NotiViewResponse> = retrofit.create(ApiServiceNotiView::class.java).getNotiView(query,language, sortBy ,apikey)
            if(myResponse.isSuccessful){
                Log.i("Noti View",myResponse.toString());
                val response: NotiViewResponse? = myResponse.body()
                if(response != null && response.notiView != null){
                    Log.i("johnpinilla",  response.toString())
                    runOnUiThread{
                        adapter.updateList(response.notiView)
                        binding.progressBar.isVisible = false;
                    }
                }

            }else{
                Log.i("johnpinilla", "no funciona")
            }
        }
    }
    private fun navigateToDetail(id:String){

        val intent = Intent(this, DetailSuperHereoActivity::class.java)
        intent.putExtra(DetailSuperHereoActivity.EXRTRA_ID,id)
        startActivity(intent)
    }
}