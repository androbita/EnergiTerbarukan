package com.android.energiterbarukan.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.android.energiterbarukan.R
import com.android.energiterbarukan.database.User
import com.android.energiterbarukan.databinding.ActivityUserRegisterBinding
import com.android.energiterbarukan.helper.ViewModelFactory
import com.android.energiterbarukan.ui.login.UserLoginActivity

class UserRegisterActivity : AppCompatActivity() {

    private lateinit var userRegisterViewModel: UserRegisterViewModel
    private lateinit var _binding: ActivityUserRegisterBinding
    private val binding get() = _binding!!
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        _binding = ActivityUserRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = User()
        userRegisterViewModel = obtainViewModel(this@UserRegisterActivity)

        binding.btnRegist.setOnClickListener {
            val username = binding?.etUsername?.text.toString().trim()
            val password = binding?.etPassword?.text.toString().trim()
            val email = binding?.etEmail?.text.toString().trim()

            when {
                username.isEmpty() -> {
                    binding?.etUsername?.error = "Tidak Boleh Kosong"
                }

                password.isEmpty() -> {
                    binding?.etPassword?.error = "Tidak Boleh Kosong"
                }

                email.isEmpty() -> {
                    binding?.etEmail?.error = "Tidak Boleh Kosong"
                }
                else -> {
                    user.let { user ->
                        user?.username = username
                        user?.password = password
                        user?.email = email
                    }
                    userRegisterViewModel.insert(user as User)
                    Toast.makeText(this, "User Berhasil dibuat", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@UserRegisterActivity, UserLoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding
    }

    private fun obtainViewModel(activity: AppCompatActivity): UserRegisterViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(UserRegisterViewModel::class.java)
    }
}