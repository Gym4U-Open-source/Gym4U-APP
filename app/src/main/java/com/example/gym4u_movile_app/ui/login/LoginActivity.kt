package com.example.gym4u_movile_app.ui.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.gym4u_movile_app.MainActivity
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.ActivityLoginBinding
import com.example.gym4u_movile_app.entities.FollowerUser
import com.example.gym4u_movile_app.entities.Login
import com.example.gym4u_movile_app.entities.LoginResponse
import com.example.gym4u_movile_app.entities.Role
import com.example.gym4u_movile_app.entities.User
import com.example.gym4u_movile_app.services.LoginService
import com.example.gym4u_movile_app.util.AppPreferences
import com.example.gym4u_movile_app.util.RetrofitBuilder
import com.example.gym4u_movile_app.util.UtilFn.Companion.showShortToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var preferences: AppPreferences
    companion object {
        fun apiIsHigherThat(apiCode: Int): Boolean {
            return Build.VERSION.SDK_INT > apiCode
        }
        fun systemVisibility(): Int {
            if (apiIsHigherThat(Build.VERSION_CODES.Q))
                return WindowInsets.Type.statusBars() or WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            return View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    private val loginService = RetrofitBuilder.build().create(LoginService::class.java)

    private fun initSystemUiVisibility() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if(apiIsHigherThat(Build.VERSION_CODES.Q))
            window.decorView.windowInsetsController?.hide(systemVisibility())
        else window.decorView.systemUiVisibility = systemVisibility()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
        preferences = AppPreferences(this)
        if(preferences.getUser().id != 0)
            toMainApp()
        initSystemUiVisibility()
    }

    private fun toMainApp() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun login(): Login {
        return Login(binding.etUsername.text.toString().trim(), binding.etPassword.text.toString().trim())
    }

    private fun isValid(): Boolean {
        return binding.etPassword.text.trim().isNotEmpty() && binding.etUsername.text.trim().isNotEmpty()
    }

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
    }
}