package com.example.j_test_webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.j_test_webview.data.web.model.ResultUrlGet
import com.example.j_test_webview.databinding.ActivityMainBinding
import com.example.j_test_webview.domain.util.Constants
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        urlSet()
    }

    private fun urlSet(){
        val queue = Volley.newRequestQueue(this)
        val url = Constants.BASE_URL
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val res = Gson().fromJson(response,ResultUrlGet::class.java )
                if(res.url != null){
                    val bundle = Bundle()
                    bundle.putString("url", res.url)
                    findNavController(R.id.frContainer).navigate(R.id.webFragment, bundle)

                }else{
                    findNavController(R.id.frContainer).navigate(R.id.signFragment)
                }
            },
            { error ->
                Log.d("response_1", error.toString())
            })
        queue.add(stringRequest)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}