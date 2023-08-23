package com.example.j_test_webview.ui.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.j_test_webview.MainActivity
import com.example.j_test_webview.databinding.DialogErrorBinding
import com.example.j_test_webview.databinding.DialogExitBinding
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

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                dialogExit()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

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