package com.example.gym4u_movile_app.ui.register

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.ActivityRegisterBinding
import com.example.gym4u_movile_app.entities.Register
import com.example.gym4u_movile_app.entities.User
import com.example.gym4u_movile_app.enums.Roles
import com.example.gym4u_movile_app.services.UserService
import com.example.gym4u_movile_app.ui.login.LoginActivity
import com.example.gym4u_movile_app.util.RetrofitBuilder
import com.example.gym4u_movile_app.util.UtilFn.Companion.areDifferent
import com.example.gym4u_movile_app.util.UtilFn.Companion.fullScreenUi
import com.example.gym4u_movile_app.util.UtilFn.Companion.isEmail
import com.example.gym4u_movile_app.util.UtilFn.Companion.isEmpty
import com.example.gym4u_movile_app.util.UtilFn.Companion.selectedString
import com.example.gym4u_movile_app.util.UtilFn.Companion.showShortToast
import com.example.gym4u_movile_app.util.UtilFn.Companion.startActivityAndClean
import com.example.gym4u_movile_app.util.UtilFn.Companion.textString
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val userService = RetrofitBuilder.build().create(UserService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        fullScreenUi()
        initClickers()
    }

    private fun formIsValid(): Boolean {
        if(binding.etRegisterEmail.isEmpty() ||
            binding.etRegisterName.isEmpty() ||
            binding.etRegisterUsername.isEmpty() ||
            binding.etRegisterPassword.isEmpty() ||
            binding.etRegisterRepeatPassword.isEmpty() ||
            binding.etRegisterLastname.isEmpty())
        {
            showShortToast(R.string.fill_all_fields)
            return false
        }

        if(binding.etRegisterPassword.textString().length <= 5) {
            showShortToast(getString(R.string.password_should_be_longer).replace("$1", "5"))
            return false
        }

        if(binding.etRegisterPassword.areDifferent(binding.etRegisterRepeatPassword)){
            showShortToast(R.string.passwords_must_be_equals)
            return false
        }
        if(!binding.etRegisterEmail.textString().isEmail()) {
            showShortToast(R.string.insert_a_valid_email)
            return false
        }

        return true
    }

    private fun getUserType() = if(binding.spUserTypes.selectedString() == "User") Roles.NORMAL.name else Roles.COACH.name

    private fun toLogin(ifRegister: Boolean = false) = startActivityAndClean(LoginActivity::class.java).apply {
        if(ifRegister) {
            showShortToast(R.string.register_successfully)
        }
    }

    private fun toRegister(): Register {
        return Register(
            username = binding.etRegisterUsername.textString(),
            password = binding.etRegisterPassword.textString(),
            email = binding.etRegisterEmail.textString(),
            roles = listOf(getUserType())
        )
    }

    private fun register() {
        userService.register(toRegister())
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) = if(response.isSuccessful) toLogin(true) else showShortToast(R.string.ups_try_again)
                override fun onFailure(call: Call<User>, t: Throwable) = showShortToast(R.string.ups_try_again)
            })
    }

    private fun initClickers() {
        binding.btSignUp.setOnClickListener {
            if(formIsValid())
                register()
        }
        binding.tvSignInAction.setOnClickListener { toLogin() }
    }

    private fun initViews() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.user_types,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spUserTypes.adapter = adapter

    }


}