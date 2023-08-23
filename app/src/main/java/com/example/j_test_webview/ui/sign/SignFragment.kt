package com.example.j_test_webview.ui.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.j_test_webview.databinding.FragmentSignBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignFragment : Fragment() {

    private lateinit var binding: FragmentSignBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}