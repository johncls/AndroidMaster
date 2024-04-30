package com.johnpinilla.androidmaster.blaaier


import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.johnpinilla.androidmaster.databinding.ActivityBlaaierBinding

class BlaaierActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlaaierBinding // Declarar la variable de binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlaaierBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenUrl.setOnClickListener {
            var url = binding.etURL.text.toString()
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://$url"
            }
            Log.i("RUl",url )

            binding.webView.webViewClient = WebViewClient()
            binding.webView.loadUrl(url)
        }
    }
}