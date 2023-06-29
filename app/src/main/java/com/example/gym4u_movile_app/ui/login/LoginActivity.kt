package com.example.gym4u_movile_app.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gym4u_movile_app.MainActivity
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.ActivityLoginBinding
import com.example.gym4u_movile_app.models.Login
import com.example.gym4u_movile_app.models.LoginResponse
import com.example.gym4u_movile_app.services.UserService
import com.example.gym4u_movile_app.ui.register.RegisterActivity
import com.example.gym4u_movile_app.util.AppPreferences.Companion.preferences
import com.example.gym4u_movile_app.util.RetrofitBuilder
import com.example.gym4u_movile_app.util.UtilFn.Companion.fullScreenUi
import com.example.gym4u_movile_app.util.UtilFn.Companion.showShortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginService = RetrofitBuilder.build().create(UserService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()

        fullScreenUi()
    }

    override fun onResume() {
        if(preferences.isNotDefault())
            toMainApp()
        super.onResume()
    }

    private fun toMainApp() = startActivity(Intent(this, MainActivity::class.java))
    private fun login() = Login(binding.etUsername.text.toString().trim(), binding.etPassword.text.toString().trim())
    private fun isValid() = binding.etPassword.text.trim().isNotEmpty() && binding.etUsername.text.trim().isNotEmpty()
    private fun toRegister() = startActivity(Intent(this, RegisterActivity::class.java))

    private fun initClickers() {
        binding.btSignIn.setOnClickListener {
            if (isValid())
                loginService
                    .login(login())
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            if(response.isSuccessful) {
                                val res = response.body()!!
                                preferences.saveUser(res.resource)
                                toMainApp()
                            }
                            else showShortToast(this@LoginActivity, R.string.incorrect_credentials)
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Log.d("loginActivity", t.toString())
                        }

                    })
            else showShortToast(this, R.string.fill_all_fields)
        }
        binding.tvRegisterAction.setOnClickListener { toRegister() }
    }
}