package com.example.matrimony.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.matrimony.R
import com.example.matrimony.model.ErrorResponse
import com.example.matrimony.model.LoginResponse
import com.example.matrimony.model.PasswordInfo
import com.example.matrimony.model.ProfileInfo
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private var radioGroup: RadioGroup? = null
private lateinit var radioButton: RadioButton
lateinit var hideButton: Button
lateinit var et_password: EditText


class HideProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hide_profile)

        toolbar = findViewById(R.id.toolbar)
        hideButton = findViewById(R.id.hideButton)
        radioGroup = findViewById(R.id.radioGroup2)
        et_password = findViewById(R.id.et_password)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setTitle("Hide Profile");


        hideButton.setOnClickListener(View.OnClickListener {
            val selectedOption: Int = radioGroup!!.checkedRadioButtonId
            radioButton = findViewById(selectedOption)

        if(et_password.text.toString().isNullOrBlank() ||
                et_password.text.toString().isNullOrEmpty()){
            Toast.makeText(this@HideProfileActivity, "Please Enter PAssword", Toast.LENGTH_SHORT).show()
        }else{
            hideprofileApi(radioButton.text.toString(), et_password.text.toString())
        }
        })

    }

    private fun hideprofileApi(hide_duration: String, password: String) {

        try {
            if (Utils.isConnectingToInternet(this@HideProfileActivity)) {
                //pr_bar!!.visibility = View.VISIBLE
                val retIn = ApiInterface.RetrofitInstance.getRetrofitInstance()
                    .create(ApiInterface::class.java)
                val profilehideInfo = ProfileInfo(hide_duration, password)
                retIn.HideProfileData(profilehideInfo).enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.code() == 200) {
                            //  progressBar.visibility=View.VISIBLE
                            val responseBody: LoginResponse? = response.body()
                            if (responseBody != null) {
                                println(responseBody.toString())
                                Utils.toast(
                                    this@HideProfileActivity,
                                    responseBody.message.toString()
                                )
                                finish()
                            }
                           // pr_bar!!.visibility = View.GONE
                        } else {
                            val gson = Gson()
                            val errorResponse: ErrorResponse = gson.fromJson(
                                response.errorBody()!!.string(),
                                ErrorResponse::class.java
                            )
                            Toast.makeText(this@HideProfileActivity, errorResponse.message.toString(), Toast.LENGTH_SHORT)
                                .show()

                         //   pr_bar!!.visibility = View.GONE
                            //progressBar.visibility=View.GONE
                        }
                    }
                })
            } else {
                Utils.toast(this@HideProfileActivity, "Please Check Internet Connection")
            }
        } catch (err: Exception) {
            err.printStackTrace()
        }
    }
}