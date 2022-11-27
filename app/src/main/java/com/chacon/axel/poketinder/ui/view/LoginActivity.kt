package com.chacon.axel.poketinder.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.chacon.axel.poketinder.databinding.ActivityLoginBinding
import com.chacon.axel.poketinder.ui.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

//    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        sharedPreferenceUtil = SharedPreferenceUtil().also {
//            it.setSharedPreference(this)
//        }
//
//        binding.btnLogin.setOnClickListener {
//            startLogin()
//        }
//
//        binding.btnRegister.setOnClickListener {
//            startActivity(Intent(this, RegisterActivity::class.java))
//        }
//    }
//
//    fun startLogin() {
//        val email = binding.edtEmail.text.toString()
//        val password = binding.edtPassword.text.toString()
//
//        val user: User? = sharedPreferenceUtil.getUser()
//
//        if (email == user?.email && password == user.password) {
//            startActivity(Intent(this, MainActivity::class.java))
//        } else {
//            Toast.makeText(this, "Error usuario", Toast.LENGTH_SHORT).show()
//        }
//    }

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = LoginViewModel(this)

        binding.btnLogin.setOnClickListener{
            startLogin()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        loginViewModel.emptyFieldError.observe(this) {
            Toast.makeText(this, "Ingrese los datos del usuario", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.fieldsAuthenticateError.observe(this) {
            Toast.makeText(this, "Error usuario", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.goSuccesActivity.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun startLogin(){
        loginViewModel.validateInputs(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )
    }
}