package com.example.matrimony.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.matrimony.R
import com.example.matrimony.model.Login
import com.example.matrimony.model.LoginResponse
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import com.example.poultry_i.storageHelpers.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


lateinit var toolbar: Toolbar

lateinit var loginButton: Button
lateinit var registerButton: Button
lateinit var edit_login: EditText
lateinit var edit_text_password: EditText




class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setTitle("Login");
        loginButton = findViewById(R.id.loginButton)
        edit_login = findViewById(R.id.edit_login)
        edit_text_password = findViewById(R.id.edit_text_password)
        registerButton = findViewById(R.id.registerButton)

        loginButton.setOnClickListener(View.OnClickListener {
            try {

                if (edit_login.text.isNullOrBlank() || edit_text_password.text.isNullOrBlank()) {
                    toast("Please Enter Valid Username And Password")
                } else {
                    signin(edit_login.text.toString(), edit_text_password.text.toString())
                }
            } catch (err: Exception) {
                err.printStackTrace()
            }
        })

        registerButton.setOnClickListener(View.OnClickListener {
            try {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            } catch (err: Exception) {
                err.printStackTrace()
            }
        })


    }


    private fun signin(mobile: String, password: String) {
        if (Utils.isConnectingToInternet(this)) {
            val retIn =
                ApiInterface.RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
            val signInInfo = Login(mobile, password)
            retIn.login(signInInfo).enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {

                    if (response.code() == 200) {
                       // progressBar.visibility = View.GONE
                        val responseBody: LoginResponse? = response.body()
                        if (responseBody != null) {
                           // username = responseBody.loginDetails!!.name.toString()
                            Utils.token = responseBody.token.toString()
                            PreferenceHelper.setStringPreference(
                                this@LoginActivity,
                                "token",
                                Utils.token
                            )
                        }
                        Toast.makeText(this@LoginActivity, responseBody!!.message.toString(), Toast.LENGTH_SHORT)
                            .show()

                        goToNextScreen()


                    } else {
                        Toast.makeText(this@LoginActivity, response.message(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        } else {
            //progressBar.visibility = View.GONE
//            Utils.showIndefiniteSnackBar(
//                ll_login_root_view,
//                "You're offline, Please check your network connection."
//            )

        }
    }

    private fun goToNextScreen() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}