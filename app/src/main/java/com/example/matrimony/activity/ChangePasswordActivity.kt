package com.example.matrimony.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.matrimony.R
import com.example.matrimony.model.LoginResponse
import com.example.matrimony.model.PasswordInfo
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import com.example.poultry_i.common.Utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var old_password: TextView
    lateinit var new_password: TextView
    lateinit var new_confirm_password: TextView
    private var pr_bar: ProgressBar? = null
    lateinit var changeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password)
        pr_bar = findViewById(R.id.pr_bar)
        changeButton = findViewById(R.id.changeButton)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setTitle("Change Password");
        new_confirm_password = findViewById(R.id.new_confirm_password)
        new_password = findViewById(R.id.new_password)
        old_password = findViewById(R.id.old_password)

        changeButton.setOnClickListener(View.OnClickListener {

            try {
                if (old_password.text.isNullOrBlank()) {
                    toast(this@ChangePasswordActivity,"Please Enter Old Password")
                } else if (new_password.text.isNullOrBlank()){
                    toast(this@ChangePasswordActivity,"Please Enter New Password")
                }else if (new_confirm_password.text.isNullOrBlank()){
                    toast(this@ChangePasswordActivity,"Please Enter New Confirm Password")
                }else if(!new_password.text.toString().equals(new_confirm_password.text.toString())){
                    toast(this@ChangePasswordActivity,"New and Confirm Password Must be Same")
                }else{
                    changepassword(old_password.text.toString(),new_password.text.toString(),new_confirm_password.text.toString())
                }
            } catch (err: Exception) {
                err.printStackTrace()
            }
        })
    }

    private fun changepassword(password: String, new_password: String, confirm_password: String) {

        try {
            if (Utils.isConnectingToInternet(this@ChangePasswordActivity)) {
                pr_bar!!.visibility = View.VISIBLE
                val retIn = ApiInterface.RetrofitInstance.getRetrofitInstance()
                    .create(ApiInterface::class.java)
                val passwordInfo = PasswordInfo(password, new_password,confirm_password)
                retIn.ChanePasswordData(passwordInfo).enqueue(object : Callback<LoginResponse> {
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
                                if(Utils.token != null){
                                    Utils.token = tokenonstart
                                }
                                toast(this@ChangePasswordActivity,responseBody.message.toString())
                                gotoLoginActivity()
                            }
                            pr_bar!!.visibility = View.GONE
                        }else{
                            toast(this@ChangePasswordActivity,responseBody!!.message.toString())
                            pr_bar!!.visibility = View.GONE
                            //progressBar.visibility=View.GONE
                        }
                    }
                })
            }else{
                toast(this@ChangePasswordActivity,"Please Chaeck Internet Connection")
            }
        } catch (err: Exception) {
            err.printStackTrace()
        }
    }



    private fun gotoLoginActivity() {
        val intent = Intent(this@ChangePasswordActivity, LoginActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

}
