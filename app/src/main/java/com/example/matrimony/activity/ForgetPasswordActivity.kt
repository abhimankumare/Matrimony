package com.example.matrimony.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.matrimony.R
import com.example.matrimony.model.LoginResponse
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import com.example.poultry_i.storageHelpers.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


lateinit var send_password: Button
lateinit var tv_email_id: EditText
private var pr_bar_for: ProgressBar? = null

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setTitle("Forget Password");
        send_password = findViewById(R.id.send_password)
        pr_bar_for = findViewById(R.id.pr_bar_for)
        tv_email_id = findViewById(R.id.tv_email_id)


        send_password.setOnClickListener(View.OnClickListener {

            try {
              if(tv_email_id.text.toString().isNullOrBlank()){
                  toast("Please Enter Email Id")
              }else if(!Patterns.EMAIL_ADDRESS.matcher(tv_email_id.text.toString()).matches()){
                    toast("Please Enter Valid Email Id")
              }else {
                  callApiForResetPassword()

              }
            } catch (err: Exception) {
                err.printStackTrace()
            }

        })


    }

    private fun callApiForResetPassword() {
        try {
            if (Utils.isConnectingToInternet(this@ForgetPasswordActivity)) {
                pr_bar_for!!.visibility=View.VISIBLE
                val retIn = ApiInterface.RetrofitInstance.getRetrofitInstance()
                    .create(ApiInterface::class.java)
                retIn.ForgetPassData().enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        pr_bar_for!!.visibility=View.GONE
                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.code() == 200) {
                              pr_bar_for!!.visibility=View.GONE
                            val responseBody: LoginResponse? = response.body()
                            if (responseBody != null) {
                                Toast.makeText(this@ForgetPasswordActivity, ""+responseBody.message.toString(), Toast.LENGTH_SHORT).show()
                                PreferenceHelper.clearValueForKey(this@ForgetPasswordActivity, "token")
                                toast("Password Sent Successfully")
                                if(Utils.token != null){
                                    Utils.token = tokenonstart
                                }
                                val intent = Intent(this@ForgetPasswordActivity, LoginActivity::class.java)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)
                                finish()
                            }
                        }else{
                            pr_bar_for!!.visibility=View.GONE
                           // toast("Something Went Wrong")
                            toast("Password Sent Successfully")
                            if(Utils.token != null){
                                Utils.token = tokenonstart
                            }
                            val intent = Intent(this@ForgetPasswordActivity, LoginActivity::class.java)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            finish()
                            //progressBar.visibility=View.GONE
                        }
                    }
                })
            }else{
                pr_bar_for!!.visibility=View.GONE
                toast("Something Went Wrong")
            }
        } catch (err: Exception) {
            err.printStackTrace()
        }



    }

    override fun onBackPressed() {
        val intent = Intent(this@ForgetPasswordActivity, LoginActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}