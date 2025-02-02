package com.android.energiterbarukan.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.energiterbarukan.R
import com.android.energiterbarukan.databinding.ActivityUserLoginBinding
import com.android.energiterbarukan.helper.ViewModelFactory
import com.android.energiterbarukan.ui.homepage.MainActivity
import com.android.energiterbarukan.ui.register.UserRegisterActivity

class UserLoginActivity : AppCompatActivity() {

    private var _binding: ActivityUserLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        _binding = ActivityUserLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegis.setOnClickListener {
            val intent = Intent(this@UserLoginActivity, UserRegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            val userLoginViewModel = obtainViewModel(this@UserLoginActivity)
            userLoginViewModel.getAllUser().observe(this) { userList ->
                if (userList != null) {
                    for (userpass in userList) {
                        if (userpass.username == username && userpass.password == password) {
                            val intent = Intent(this@UserLoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                } else {
                    Toast.makeText(this, "Username kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): LoginViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LoginViewModel::class.java)
    }
}