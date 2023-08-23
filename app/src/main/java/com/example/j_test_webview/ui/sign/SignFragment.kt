package com.example.j_test_webview.ui.sign

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    private var textName:String = ""
    private var textPass:String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.formContainer.etName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textName = s.toString()
                textChan()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.formContainer.etPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textPass = s.toString()
                textChan()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.formContainer.apply {
            btnClick.setOnClickListener {
                binding.formContainer.layoutForm.visibility = View.INVISIBLE
                binding.pbIndicator.visibility = View.VISIBLE
            }
            tvCreateAccount.setOnClickListener {
                when(tvCreateAccount.text){
                    "Create account" -> {
                        tvLabel.text = "Create account"
                        btnClick.text = "Sign Up"
                        tvCreateAccount.text = "Login"
                        etPassword.visibility = View.VISIBLE
                        textChan()
                    }
                    "Login" -> {
                        tvLabel.text = "Sign in to continue"
                        btnClick.text = "Login"
                        tvCreateAccount.text = "Create account"
                        etPassword.visibility = View.VISIBLE
                        textChan()
                    }
                }
            }
            tvForgot.setOnClickListener {
                tvLabel.text = "Restore access"
                btnClick.text = "Forgot"
                etPassword.visibility = View.GONE
                textChan()
            }
        }


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

    private fun textChan(): Boolean{
        return if(
            textName.length > 5 && textPass.length > 5 ||
            textName.length > 5 && binding.formContainer.etPassword.visibility == View.GONE){
            binding.formContainer.btnClick.isEnabled = true
            true
        }else{
            binding.formContainer.btnClick.isEnabled = false
            false
        }
    }
}