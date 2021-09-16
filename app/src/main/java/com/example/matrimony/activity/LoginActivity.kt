package com.example.matrimony.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.matrimony.R
import com.example.matrimony.model.*
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import com.example.poultry_i.common.Utils.profile_saved
import com.example.poultry_i.storageHelpers.PreferenceHelper
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


lateinit var toolbar: Toolbar

lateinit var loginButton: Button
lateinit var registerButton: Button
lateinit var edit_login: EditText
lateinit var edit_text_password: EditText
lateinit var ll_login_root_view: LinearLayout

private var pr_bar: ProgressBar? = null


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setTitle("Login");
        loginButton = findViewById(R.id.loginButton)
        pr_bar = findViewById(R.id.pr_bar)
        edit_login = findViewById(R.id.edit_login)
        edit_text_password = findViewById(R.id.edit_text_password)
        registerButton = findViewById(R.id.registerButton)
        ll_login_root_view = findViewById(R.id.ll_login_root_view)

        getMasterData()

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
        println("In Master Data signin")
        if (Utils.isConnectingToInternet(this)) {
            pr_bar!!.visibility=View.VISIBLE
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
                    pr_bar!!.visibility=View.GONE
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.code() == 200) {
                        pr_bar!!.visibility=View.GONE
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
                        pr_bar!!.visibility=View.GONE
                        val gson = Gson()
                        val errorResponse: ErrorResponse = gson.fromJson(
                            response.errorBody()!!.string(),
                            ErrorResponse::class.java
                        )
                        Toast.makeText(this@LoginActivity, errorResponse.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        } else {
            //progressBar.visibility = View.GONE
            pr_bar!!.visibility=View.GONE
            Utils.showIndefiniteSnackBar(
                ll_login_root_view,
                "You're offline, Please check your network connection."
            )

        }
    }

    private fun goToNextScreen() {
        getProfileData()
    }



    fun getProfileData() {
        println("In Master Data getProfileData")
        try {
            if (Utils.isConnectingToInternet(this@LoginActivity)) {
                val retIn = ApiInterface.RetrofitInstance.getRetrofitInstance()
                    .create(ApiInterface::class.java)
                retIn.ProfileData().enqueue(object : Callback<ProfileResponse> {
                    override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<ProfileResponse>,
                        response: Response<ProfileResponse>
                    ) {
                        if (response.code() == 200) {
                            //  progressBar.visibility=View.VISIBLE
                            val responseBody: ProfileResponse? = response.body()
                            if (responseBody != null) {
                                println(responseBody.toString())
                                profile_saved = responseBody.userDetails!!.profile_saved.toString()
                                if(profile_saved.equals("0")){
                                    Utils.showDialog1(
                                        "Please Complete Your Profile Data",
                                        DialogInterface.OnClickListener { dialog, which ->
                                            when (which) {
                                                DialogInterface.BUTTON_POSITIVE -> {
                                                    dialog.dismiss()
                                                    val intent = Intent(this@LoginActivity, BasicDataActivity::class.java)
                                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                                    startActivity(intent)
                                                    finish()
                                                }
                                            }
                                        }, this@LoginActivity
                                    )
                                }else{
                                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                    finish()

                                }

                            }
                        }else{
                            //progressBar.visibility=View.GONE
                        }
                    }
                })
            }else{
            }
        } catch (err: Exception) {
            err.printStackTrace()
        }
    }

    fun getMasterData() {
        println("In Master Data 0")
        try {
            if (Utils.isConnectingToInternet(this@LoginActivity)) {
                val retIn = ApiInterface.RetrofitInstance.getRetrofitInstance()
                    .create(ApiInterface::class.java)
                retIn.getMasterData().enqueue(object : Callback<MasterResponse> {
                    override fun onFailure(call: Call<MasterResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<MasterResponse>,
                        response: Response<MasterResponse>
                    ) {
                        if (response.code() == 200) {
                            //  progressBar.visibility=View.VISIBLE
                            val responseBody: MasterResponse? = response.body()
                            if (responseBody != null) {
                                listeducation = responseBody.education as ArrayList<MasterContent>
                                listoccupation = responseBody.occupation as ArrayList<MasterContent>
                                listreligion= responseBody.religion as ArrayList<MasterContent>
                                listhoroscope= responseBody.horoscope as ArrayList<MasterContent>
                                listemployed_sector= responseBody.employed_sector as ArrayList<MasterContent>
                                listincome= responseBody.income as ArrayList<MasterContent>
                                listmother_tongue= responseBody.mother_tongue as ArrayList<MasterContent>
                                listcaste= responseBody.caste as ArrayList<MasterContent>
                                listLanguage= responseBody.Language as ArrayList<MasterContent>



                                for(i in 0 until listeducation.size){
                                    spinnereducationArrayIds.add(listeducation[i].id)
                                    spinnereducationArray.add(listeducation[i].name)
                                }
                                for(i in 0 until listoccupation.size){
                                    spinneroccupationArrayIds.add(listoccupation[i].id)
                                    spinneroccupationArray.add(listoccupation[i].name)
                                }
                                for(i in 0 until listreligion.size){
                                    spinnerreligionArrayIds.add(listreligion[i].id)
                                    spinnerreligionArray.add(listreligion[i].name)
                                }


                                for(i in 0 until listhoroscope.size){
                                    spinnerhoroscopeArrayIds.add(listhoroscope[i].id)
                                    spinnerhoroscopeArray.add(listhoroscope[i].horoscope_name)
                                }
                                for(i in 0 until listemployed_sector.size){
                                    spinneremployed_sectorArrayIds.add(listemployed_sector[i].id)
                                    spinneremployed_sectorArray.add(listemployed_sector[i].employed_sector)
                                }
                                for(i in 0 until listincome.size){
                                    spinnerincomeArrayIds.add(listincome[i].id)
                                    spinnerincomeArray.add(listincome[i].income)
                                }
                                for(i in 0 until listmother_tongue.size){
                                    spinnermother_tongueArrayIds.add(listmother_tongue[i].id)
                                    spinnermother_tongueArray.add(listmother_tongue[i].name)
                                }
                                for(i in 0 until listcaste.size){
                                    spinnercasteArrayIds.add(listcaste[i].id)
                                    spinnercasteArray.add(listcaste[i].name)
                                }
                                for(i in 0 until listLanguage.size){
                                    spinnerLanguageArrayIds.add(listLanguage[i].id)
                                    spinnerLanguageArray.add(listLanguage[i].name)
                                }

                            }
                        }else{
                            //progressBar.visibility=View.GONE
                        }
                    }
                })
            }else{
            }
        } catch (err: Exception) {
            err.printStackTrace()
        }

    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}