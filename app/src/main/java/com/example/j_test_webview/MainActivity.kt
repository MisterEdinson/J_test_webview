package com.example.j_test_webview

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.j_test_webview.data.web.model.ResultUrlGet
import com.example.j_test_webview.databinding.ActivityMainBinding
import com.example.j_test_webview.databinding.DialogErrorBinding
import com.example.j_test_webview.domain.util.Constants
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkInternetConnection()
    }

    private fun urlSet() {
        val queue = Volley.newRequestQueue(this)
        val url = Constants.BASE_URL
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val res = Gson().fromJson(response, ResultUrlGet::class.java)
                res.url?.let {
                    //appNav(R.id.signFragment)
                    appNav(R.id.webFragment, res.url)
                } ?: run {
                    appNav(R.id.signFragment)
                }
            },
            { error ->
                Log.d("response_1", error.toString())
            })
        queue.add(stringRequest)
    }

    private fun appNav(id: Int, url: String) {
        val bundle = Bundle()
        bundle.putString("url", url)
        findNavController(R.id.frContainer).navigate(id, bundle)
    }

    private fun appNav(id: Int) {
        findNavController(R.id.frContainer).navigate(id)
    }

    private fun checkInternetConnection() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            urlSet()
        } else {
            showNoInternetDialog()
        }
    }

    private fun showNoInternetDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogBind = DialogErrorBinding.inflate(LayoutInflater.from(this), null, false)
        builder.setView(dialogBind.root)
        val dialog = builder.create()

        dialogBind.tvBtnOkDialogErr.setOnClickListener {
            dialog.dismiss()
            checkInternetConnection()
        }
        dialog.show()
    }

    fun closeApp() {
        finish()
        if (isTaskRoot) {
            finishAffinity()
        }
    }
}