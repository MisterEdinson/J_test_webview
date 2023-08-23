package com.example.j_test_webview.ui.web

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.j_test_webview.MainActivity
import com.example.j_test_webview.R
import com.example.j_test_webview.databinding.DialogExitBinding
import com.example.j_test_webview.databinding.FragmentWebBinding
import com.example.j_test_webview.ui.web.dop.SharedPref
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebFragment : Fragment() {
    private lateinit var binding: FragmentWebBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webPage.settings.javaScriptEnabled = true
        binding.webPage.webViewClient = WebViewClient()
        val lastUrl = SharedPref(context).read("last_url")
        val url = arguments?.getString("url")

        lastUrl?.let {
            binding.webPage.loadUrl(lastUrl)
            Toast.makeText(context, lastUrl, Toast.LENGTH_LONG).show()
        } ?: run {
            if(url != null){
                binding.webPage.loadUrl(url)
                Toast.makeText(context, url, Toast.LENGTH_LONG).show()
            }
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                dialogExit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onPause() {
        super.onPause()
        SharedPref(context).save("last_url", binding.webPage.url)
    }

    private fun dialogExit() {
        val builder = AlertDialog.Builder(binding.root.context)
        val dialogBind =
            DialogExitBinding.inflate(LayoutInflater.from(binding.root.context), null, false)
        builder.setView(dialogBind.root)
        val dialog = builder.create()

        dialogBind.tvNoDialogExit.setOnClickListener {
            dialog.dismiss()
        }
        dialogBind.tvOkDialogExit.setOnClickListener {
            dialog.dismiss()
            val activity = requireActivity() as MainActivity
            activity.closeApp()
        }
        dialog.show()
    }
}

