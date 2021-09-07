package com.example.matrimony.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimony.R
import com.example.matrimony.adapter.SelectionAdapter
import com.example.matrimony.model.MasterContent
import com.example.matrimony.model.MasterResponse
import com.example.matrimony.model.SignUpModel
import com.example.matrimony.model.SignUpResponse
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import com.example.poultry_i.storageHelpers.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


lateinit var ll_personal_details: LinearLayout

lateinit var ll_register_root_view: LinearLayout


lateinit var rv_selection: RecyclerView
lateinit var toolbar1: Toolbar
private var listHeight: ArrayList<MasterContent> = arrayListOf()
private var listState: ArrayList<MasterContent> = arrayListOf()
private var listcities: ArrayList<MasterContent> = arrayListOf()
private var listeducation: ArrayList<MasterContent> = arrayListOf()
var listoccupation: ArrayList<MasterContent> = arrayListOf()
private var listreligion: ArrayList<MasterContent> = arrayListOf()

private var listhoroscope: ArrayList<MasterContent> = arrayListOf()
private var listemployed_sector: ArrayList<MasterContent> = arrayListOf()
private var listincome: ArrayList<MasterContent> = arrayListOf()
private var listmother_tongue: ArrayList<MasterContent> = arrayListOf()
private var listcaste: ArrayList<MasterContent> = arrayListOf()
private var listLanguage: ArrayList<MasterContent> = arrayListOf()


private var spinnerheightArray: ArrayList<String> = arrayListOf()
private var spinnerStateArray: ArrayList<String> = arrayListOf()
private var spinnerStateArrayIds: ArrayList<String> = arrayListOf()
private var spinnerCityArrayIds: ArrayList<String> = arrayListOf()
private var spinnerheightArrayIds: ArrayList<String> = arrayListOf()
var spinnereducationArrayIds: ArrayList<String> = arrayListOf()
var spinneroccupationArrayIds: ArrayList<String> = arrayListOf()
var spinnerreligionArrayIds: ArrayList<String> = arrayListOf()
var spinnerhoroscopeArrayIds: ArrayList<String> = arrayListOf()
var spinneremployed_sectorArrayIds: ArrayList<String> = arrayListOf()
var spinnerincomeArrayIds: ArrayList<String> = arrayListOf()
var spinnermother_tongueArrayIds: ArrayList<String> = arrayListOf()
var spinnercasteArrayIds: ArrayList<String> = arrayListOf()
var spinnerLanguageArrayIds: ArrayList<String> = arrayListOf()



private var spinnercitiesArray: ArrayList<String> = arrayListOf()
var spinnereducationArray: ArrayList<String> = arrayListOf()
var spinneroccupationArray: ArrayList<String> = arrayListOf()
var spinnerreligionArray: ArrayList<String> = arrayListOf()
var spinnerhoroscopeArray: ArrayList<String> = arrayListOf()
var spinneremployed_sectorArray: ArrayList<String> = arrayListOf()
var spinnerincomeArray: ArrayList<String> = arrayListOf()
var spinnermother_tongueArray: ArrayList<String> = arrayListOf()
var spinnercasteArray: ArrayList<String> = arrayListOf()
var spinnerLanguageArray: ArrayList<String> = arrayListOf()

lateinit var customerHeightTextView: AutoCompleteTextView
lateinit var customerCountryTextView: AutoCompleteTextView
lateinit var customerStateTextView: AutoCompleteTextView
lateinit var customerCityTextView: AutoCompleteTextView



lateinit var edit_text_login: EditText
lateinit var edit_text_DOB: EditText
lateinit var ed_mobileemail: EditText
lateinit var etPassword: EditText
lateinit var ed_email: EditText

lateinit var info_about: EditText

lateinit var saveButton: Button
var radioGroup: RadioGroup? = null
lateinit var radioButton: RadioButton

var token: String? = null
var state_id : String? = null
var city_id : String? = null
var height_id : String? = null
var user_type : String? = null


var education_id : String? = null
var sector_id : String? = null
var occupation_id : String? = null
var income_id : String? = null


var mothertounge_id : String? = null
var religion_id : String? = null
var caste_id : String? = null
var horoscope_id : String? = null

val calendar = Calendar.getInstance()
var responseBody: SignUpResponse? = null


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        ll_personal_details = findViewById(R.id.ll_personal_details)
        ll_register_root_view = findViewById(R.id.ll_register_root_view)

        rv_selection = findViewById(R.id.rv_selection)
        edit_text_login = findViewById(R.id.edit_text_login)
        edit_text_DOB = findViewById(R.id.edit_text_DOB)
        ed_mobileemail = findViewById(R.id.ed_mobileemail)
        etPassword = findViewById(R.id.etPassword)
        info_about = findViewById(R.id.info_about)
        ed_email = findViewById(R.id.ed_email)

        radioGroup = findViewById(R.id.radioGroup1)
        saveButton = findViewById(R.id.saveButton)
        customerHeightTextView = findViewById(R.id.customerHeightTextView)
        customerCountryTextView = findViewById(R.id.customerCountryTextView)
        customerStateTextView = findViewById(R.id.customerStateTextView)
        customerCityTextView = findViewById(R.id.customerCityTextView)


        initClickListeners()

        toolbar1 = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar1)
        getSupportActionBar()!!.setTitle("Details");

        getMasterData()

        //Set Adpater
        setSelfAdapter()

        initUICountry()



    }

    private fun initClickListeners() {


        edit_text_DOB.setOnClickListener(View.OnClickListener {
            val day = calendar[Calendar.DAY_OF_WEEK]
            val month = calendar[Calendar.MONTH]
            val year = calendar[Calendar.YEAR]
            val dpd = DatePickerDialog(
                this,
                { datePicker, nYear, nMonth, nDay ->
                    val sdf = SimpleDateFormat("yyyy-MM-dd")
                    calendar[nYear, nMonth] = nDay

                    val dateString: String = sdf.format(calendar.time)
                    edit_text_DOB.setText(dateString)
                }, year, month, day
            )
            val selectedOption: Int = radioGroup!!.checkedRadioButtonId
            radioButton = findViewById(selectedOption)
//            val today = Calendar.getInstance()
//            val twoDaysAgo = today.clone() as Calendar
//            if(radioButton.text.equals("Male")){
//                twoDaysAgo.add(Calendar.YEAR, -21)
//            }else{
//                twoDaysAgo.add(Calendar.YEAR, -18)
//            }
//            //dpd.datePicker.setMinDate(twoDaysAgo.timeInMillis)
            dpd.show()
        })

        saveButton.setOnClickListener(View.OnClickListener {

            //goToNextScreen()
            //Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()

            try {
                if (edit_text_login.text.isNullOrBlank()) {
                    Utils.toast(this@RegisterActivity,"Please Enter Full Name")
                }else if(edit_text_DOB.text.isNullOrBlank()) {
                    Utils.toast(this@RegisterActivity,"Please Enter Date Of Birth")
                }else if(customerHeightTextView.text.isNullOrBlank()) {
                    Utils.toast(this@RegisterActivity,"Please Select Height")
                }else if(customerCountryTextView.text.isNullOrBlank()) {
                    Utils.toast(this@RegisterActivity,"Please Select Country")
                }else if(customerStateTextView.text.isNullOrBlank()) {
                    Utils.toast(this@RegisterActivity,"Please Select State")
                }else if(customerCityTextView.text.isNullOrBlank()) {
                    Utils.toast(this@RegisterActivity,"Please Select City")
                }else if(ed_email.text.isNullOrBlank()) {
                    Utils.toast(this@RegisterActivity,"Please Enter Email Id")
                }else if(!Patterns.EMAIL_ADDRESS.matcher(ed_email.text.toString()).matches()) {
                    Utils.toast(this@RegisterActivity,"Please Enter Valid Email Id")
                }else if(ed_mobileemail.text.isNullOrBlank()) {
                    Utils.toast(this@RegisterActivity,"Please Enter Mobile Number")
                }else if(etPassword.text.isNullOrBlank()) {
                    Utils.toast(this@RegisterActivity,"Please Enter Password")
                }else if(info_about.text.isNullOrBlank()) {
                    Utils.toast(this@RegisterActivity,"Please Write Something About You")
                } else {
                    signUpProfile(user_type.toString(), edit_text_login.text.toString(),
                        radioButton.text.toString(), edit_text_DOB.text.toString(),
                        height_id.toString(), customerCountryTextView.text.toString(),
                        state_id.toString(), city_id.toString(),
                        ed_mobileemail.text.toString(),ed_email.text.toString(),
                        info_about.text.toString(), etPassword.text.toString(),
                        etPassword.text.toString())
                }
            } catch (err: Exception) {
                err.printStackTrace()
            }







        })
    }





    private fun signUpProfile(user_type: String,
        name: String,
        gender: String,
        birth_date: String,
        hight: String,
        contry: String,
        state_id: String,
        city_id: String,
        mobile: String,
        email: String,
        user_bio: String,
        password: String,
        confirm_password: String
    ) {

        if (Utils.isConnectingToInternet(this)) {
            val retIn =
                ApiInterface.RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
            val signInInfo = SignUpModel(user_type,name,gender,
                                        birth_date,hight,contry,state_id,
                                        city_id,mobile,email,user_bio,password,confirm_password)
            retIn.signUp(signInInfo).enqueue(object : Callback<SignUpResponse> {
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    Toast.makeText(
                        this@RegisterActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {if (response.code() == 200) {
                    responseBody = response.body()
                        if (responseBody != null) {
                            token = responseBody!!.token
                            Utils.token = responseBody!!.token.toString()
//                            PreferenceHelper.setStringPreference(
//                                this@RegisterActivity,
//                                "token",
//                                Utils.token
//                            )
                        }


                 //   {"status":false,"error":{"email":["The email has already been taken."]}}

                        Toast.makeText(this@RegisterActivity, responseBody!!.message, Toast.LENGTH_SHORT)
                            .show()

                        goToNextScreen()


                    } else {
                      //  Toast.makeText(this@RegisterActivity, responseBody!!.error.toString(), Toast.LENGTH_SHORT)
                         //   .show()
                    }
                }
            })
        } else {

            Utils.showIndefiniteSnackBar(
                ll_register_root_view,
                "You're offline, Please check your network connection."
            )

        }
    }

    private fun goToNextScreen() {
        val intent = Intent(this@RegisterActivity, BasicDataActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    private fun getMasterData() {
        try {
            if (Utils.isConnectingToInternet(this@RegisterActivity)) {
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
                                listHeight = responseBody.height as ArrayList<MasterContent>
                                listState = responseBody.state as ArrayList<MasterContent>
                                listcities = responseBody.cities as ArrayList<MasterContent>
                                listeducation = responseBody.education as ArrayList<MasterContent>
                                listoccupation = responseBody.occupation as ArrayList<MasterContent>
                                listreligion= responseBody.religion as ArrayList<MasterContent>
                                listhoroscope= responseBody.horoscope as ArrayList<MasterContent>
                                listemployed_sector= responseBody.employed_sector as ArrayList<MasterContent>
                                listincome= responseBody.income as ArrayList<MasterContent>
                                listmother_tongue= responseBody.mother_tongue as ArrayList<MasterContent>
                                listcaste= responseBody.caste as ArrayList<MasterContent>
                                listLanguage= responseBody.Language as ArrayList<MasterContent>




                                for(i in 0 until listHeight.size){
                                    spinnerheightArrayIds.add(listHeight[i].id)
                                    spinnerheightArray.add(listHeight[i].height_type)
                                }
                                for(i in 0 until listState.size){
                                    spinnerStateArrayIds.add(listState[i].id)
                                    spinnerStateArray.add(listState[i].name)
                                }
                                for(i in 0 until listcities.size){
                                    spinnerCityArrayIds.add(listcities[i].id)
                                    spinnercitiesArray.add(listcities[i].name)
                                }
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
                                    spinnermother_tongueArray.add(listmother_tongue[i].mother_tongue_name)
                                }
                                for(i in 0 until listcaste.size){
                                    spinnercasteArrayIds.add(listcaste[i].id)
                                    spinnercasteArray.add(listcaste[i].name)
                                }
                                for(i in 0 until listLanguage.size){
                                    spinnerLanguageArrayIds.add(listLanguage[i].id)
                                    spinnerLanguageArray.add(listLanguage[i].name)
                                }


                                initUIHeight()
                                initUIState()
                                initUICity()



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


    fun setView(User_Type: String) {
        //goToNextScreen()
        getSupportActionBar()!!.setTitle("Presonal Details");
        user_type == User_Type
        rv_selection.visibility = View.GONE
        ll_personal_details.visibility = View.VISIBLE
    }

    private fun setSelfAdapter() {
       // rv_selection.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rv_selection.setHasFixedSize(true);
        rv_selection.setLayoutManager(layoutManager);
        //creating a  arraylist of data
        val data: ArrayList<String> = ArrayList()
        data.add("Self")
        data.add("Relative")
        data.add("Son")
        data.add("Daughter")
        data.add("Brother")
        data.add("Sister")
        data.add("Client")
        data.add("Friend")

        //setting adapter to recycler
        rv_selection.adapter = SelectionAdapter(this@RegisterActivity,data)
    }

    private fun initUIHeight() {
        //UI reference of textView
        val customerHeight = findViewById<AutoCompleteTextView>(R.id.customerHeightTextView)
        //Create adapter
        val adapter = ArrayAdapter(
            this@RegisterActivity,
            R.layout.custome_new_spinner,
            spinnerheightArray
        )
        //Set adapter
        customerHeight.setAdapter(adapter)
        customerHeight.setOnItemClickListener { parent, view, position, id ->
            height_id = spinnerheightArrayIds.get(position)
        }

    }

    private fun initUICountry() {
        run {

            //UI reference of textView
            val customerAutoTV =
                findViewById<AutoCompleteTextView>(R.id.customerCountryTextView)

            // create list of customer
            val countryList = getCountryList()

            //Create adapter
            val adapter = ArrayAdapter(
                this@RegisterActivity,
                R.layout.custome_new_spinner,
                countryList
            )

            //Set adapter
            customerAutoTV.setAdapter(adapter)
        }
    }

    private fun initUIState() {
        run {

            //UI reference of textView
            val customerState =
                findViewById<AutoCompleteTextView>(R.id.customerStateTextView)


            //Create adapter
            val adapter = ArrayAdapter(
                this@RegisterActivity,
                R.layout.custome_new_spinner,
                spinnerStateArray
            )

            //Set adapter
            customerState.setAdapter(adapter)


            customerState.setOnItemClickListener { parent, view, position, id ->
                state_id = spinnerStateArrayIds.get(position)
            }






        }
    }

    private fun initUICity() {
        run {

            //UI reference of textView
            val customerCity =
                findViewById<AutoCompleteTextView>(R.id.customerCityTextView)

            //Create adapter
            val adapter = ArrayAdapter(
                this@RegisterActivity,
                R.layout.custome_new_spinner,
                spinnercitiesArray
            )

            //Set adapter
            customerCity.setAdapter(adapter)
            customerCity.setOnItemClickListener { parent, view, position, id ->

                city_id = spinnerCityArrayIds.get(position)
            }
        }
    }

    private fun getCountryList(): ArrayList<String> {
        val Country = ArrayList<String>()
        Country.add("India")
        return Country
    }





}