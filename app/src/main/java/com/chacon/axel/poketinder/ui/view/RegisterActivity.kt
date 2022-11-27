package com.chacon.axel.poketinder.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.chacon.axel.poketinder.databinding.ActivityRegisterBinding
import com.chacon.axel.poketinder.ui.viewmodel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {

//    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        sharedPreferenceUtil = SharedPreferenceUtil().also {
//            it.setSharedPreference(this)
//        }
//
//        binding.btnRegister.setOnClickListener {
//            val userId = "1"
//
//            val userName = binding.edtUserName.text.toString()
//            val email = binding.edtEmail.text.toString()
//            val password = binding.edtPassword.text.toString()
//
//            val user = User(
//                userId,
//                userName,
//                email,
//                password
//            )
//            sharedPreferenceUtil.saveUser(user)
//            startActivity(Intent(this, LoginActivity::class.java))
//        }
//
//        binding.btnGoLogin.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }
//    }
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerViewModel = RegisterViewModel(this)

        binding.btnRegister.setOnClickListener {
            startRegister()
        }

        binding.btnGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        registerViewModel.emptyFieldsError.observe(this) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.goSuccessActivity.observe(this) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    fun startRegister(){
        registerViewModel.validateInputs(
            binding.edtUserName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )
    }
}