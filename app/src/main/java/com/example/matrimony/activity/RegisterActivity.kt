package com.example.matrimony.activity

import android.app.DatePickerDialog
import android.content.ClipData.Item
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bfl.superapp.api.ApiName
import com.bfl.superapp.viewmodel.CommonViewModel
import com.example.matrimony.R
import com.example.matrimony.adapter.SelectionAdapter
import com.example.matrimony.model.MasterContent
import com.example.matrimony.model.MasterResponse
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import com.example.poultry_i.common.Utils.toast
import com.example.poultry_i.storageHelpers.PreferenceHelper
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Response.success
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Result.Companion.failure


lateinit var ll_career_details: LinearLayout
lateinit var ll_personal_details: LinearLayout
lateinit var ll_social_details: LinearLayout
lateinit var ll_family_details: LinearLayout
lateinit var rv_selection: RecyclerView
private lateinit var selectionAdapter: SelectionAdapter
lateinit var toolbar1: Toolbar
private var listHeight: ArrayList<MasterContent> = arrayListOf()
private var listState: ArrayList<MasterContent> = arrayListOf()
private var listcities: ArrayList<MasterContent> = arrayListOf()
private var listeducation: ArrayList<MasterContent> = arrayListOf()
private var listoccupation: ArrayList<MasterContent> = arrayListOf()
private var listreligion: ArrayList<MasterContent> = arrayListOf()
private var spinnerheightArray: ArrayList<String> = arrayListOf()
private var spinnerStateArray: ArrayList<String> = arrayListOf()
private var spinnercitiesArray: ArrayList<String> = arrayListOf()
private var spinnereducationArray: ArrayList<String> = arrayListOf()
private var spinneroccupationArray: ArrayList<String> = arrayListOf()
private var spinnerreligionArray: ArrayList<String> = arrayListOf()
lateinit var edit_text_DOB: EditText
lateinit var ed_mobileemail: EditText
lateinit var saveButton: Button
var radioGroup: RadioGroup? = null
lateinit var radioButton: RadioButton

val calendar = Calendar.getInstance()


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ll_career_details = findViewById(R.id.ll_career_details)
        ll_personal_details = findViewById(R.id.ll_personal_details)
        ll_social_details = findViewById(R.id.ll_social_details)
        ll_family_details = findViewById(R.id.ll_family_details)
        rv_selection = findViewById(R.id.rv_selection)
        edit_text_DOB = findViewById(R.id.edit_text_DOB)
        ed_mobileemail = findViewById(R.id.ed_mobileemail)
        radioGroup = findViewById(R.id.radioGroup1)
        saveButton = findViewById(R.id.saveButton)
        initClickListeners()

        toolbar1 = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar1)
        getSupportActionBar()!!.setTitle("Details");

        getMasterData()

        //Set Adpater
        setSelfAdapter()


        //Personal Details

        initUICountry()

        //Career Details

        initUISector()

        initUIIncome()
        //Social Details
        initUIMaritalStatus()
        initUIMotherTongue()

        initUICast()
        initUIHoroscope()
        initUIManglik()

        //FamilyDetails
        initUIFathersStatus()
        initUIMothersStatus()
        initUIBrother()
        initUINoofMarriedBrother()
        initUISister()
        initUINoofMarriedSister()



        val savecarrer = findViewById<View>(R.id.save_car_details) as Button
        savecarrer.setOnClickListener {
            ll_career_details.visibility = View.GONE
            ll_social_details.visibility = View.VISIBLE
            getSupportActionBar()!!.setTitle("Social Details");
        }

        val social_save = findViewById<View>(R.id.social_save) as Button
        social_save.setOnClickListener {
            ll_social_details.visibility = View.GONE
            ll_family_details.visibility = View.VISIBLE
            getSupportActionBar()!!.setTitle("Family Details");
        }

        val save_family_details = findViewById<View>(R.id.save_family_details) as Button
        save_family_details.setOnClickListener {
            try {
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            } catch (err: Exception) {
                err.printStackTrace()
            }
        }
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
            dpd.show()
        })

        saveButton.setOnClickListener(View.OnClickListener {

           // ll_personal_details.visibility = View.GONE
           // ll_career_details.visibility = View.VISIBLE
           // getSupportActionBar()!!.setTitle("Career Details");

            val selectedOption: Int = radioGroup!!.checkedRadioButtonId

            // Assigning id of the checked radio button
            radioButton = findViewById(selectedOption)

            // Displaying text of the checked radio button in the form of toast
            Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()

            signUpProfile()

        })


    }

    private fun signUpProfile() {
        val map: HashMap<String, String> = HashMap()
        map["user_type"] = "self"
        map["name"] = "Abhishek Mankumare"
        map["gender"] = "Male"
        map["birth_date"] = "1990-06-05"
        map["hight"] = "5.6"
        map["contry"] = "INDIA"
        map["state_id"] = "4"
        map["city_id"] = "19"
        map["mobile"] = "9323936081"
        map["user_bio"] = "Human Being"
        map["password"] = "123123"
        map["confirm_password"] = "123123"

        getCommonViewModel().getSignUpData(map).observe(this,{
            it.run {

                if(it.Status.equals("Success")){
                    Toast.makeText(this@RegisterActivity,""+it.message,Toast.LENGTH_LONG).show()
                }

            }
        })

    }


    fun getCommonViewModel(): CommonViewModel {
        return ViewModelProviders.of(this).get(CommonViewModel::class.java)
    }


    private fun getMasterData() {

        try {
            getCommonViewModel().getMasterData(ApiName.MASTER_URL)
                .observe(this, androidx.lifecycle.Observer {
                    it.let {

                        if (it != null) {

                            val allData = Gson().toJson(it)

                            if (Utils.isConnectingToInternet(this@RegisterActivity) && allData.toString() != "{}") {


                                Gson().fromJson(allData, MasterResponse::class.java).let { response ->
                                    if (response.toString() != "") {
                                        try {
                                            listHeight = response.height as ArrayList<MasterContent>
                                            listState = response.state as ArrayList<MasterContent>
                                            listcities = response.cities as ArrayList<MasterContent>
                                            listeducation = response.education as ArrayList<MasterContent>
                                            listoccupation = response.occupation as ArrayList<MasterContent>
                                            listreligion= response.religion as ArrayList<MasterContent>


                                            for(i in 0 until listHeight.size){
                                                spinnerheightArray.add(listHeight[i].height_type)
                                            }
                                            for(i in 0 until listState.size){
                                                spinnerStateArray.add(listState[i].name)
                                            }
                                            for(i in 0 until listcities.size){
                                                spinnercitiesArray.add(listcities[i].name)
                                            }
                                            for(i in 0 until listeducation.size){
                                                spinnereducationArray.add(listeducation[i].name)
                                            }
                                            for(i in 0 until listoccupation.size){
                                                spinneroccupationArray.add(listoccupation[i].name)
                                            }
                                            for(i in 0 until listreligion.size){
                                                spinnerreligionArray.add(listreligion[i].name)
                                            }


                                            initUIHeight()
                                            initUIState()
                                            initUICity()
                                            initUIEducatoin()
                                            initUIOccupation()
                                            initUIReligion()




                                        } catch (e: Exception) {
                                            toast(this,"No Proper data found.")
                                            println("inAppMasterData== ${e.message}")
                                        }
                                    }
                                }
                            } else {
                                toast(this,"No Proper data found.")
                            }
                        }
                    }
                })
        } catch (e: Exception) {
            toast(this,"No Proper data found.")
            //Log.i("Common FAQ", "Common FAQ Error : $e")
        }
    }


    fun setView() {
        getSupportActionBar()!!.setTitle("Presonal Details");
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
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.customerHeightTextView)
        //Create adapter
        val adapter = ArrayAdapter(
            this@RegisterActivity,
            R.layout.custome_new_spinner,
            spinnerheightArray
        )
        //Set adapter
        customerAutoTV.setAdapter(adapter)
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
            val customerAutoTV =
                findViewById<AutoCompleteTextView>(R.id.customerStateTextView)


            //Create adapter
            val adapter = ArrayAdapter(
                this@RegisterActivity,
                R.layout.custome_new_spinner,
                spinnerStateArray
            )

            //Set adapter
            customerAutoTV.setAdapter(adapter)
        }
    }

    private fun initUICity() {
        run {

            //UI reference of textView
            val customerAutoTV =
                findViewById<AutoCompleteTextView>(R.id.customerCityTextView)

            //Create adapter
            val adapter = ArrayAdapter(
                this@RegisterActivity,
                R.layout.custome_new_spinner,
                spinnercitiesArray
            )

            //Set adapter
            customerAutoTV.setAdapter(adapter)
        }
    }

    private fun getCountryList(): ArrayList<String> {
        val Country = ArrayList<String>()
        Country.add("India")
        return Country
    }



    private fun initUIEducatoin() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.educationTextView)
        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, spinnereducationArray)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration

    }


    private fun initUISector() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.selectSectorTextView)

        // create list of customer
        val customerList = getSectorList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getSectorList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("IT")
        customers.add("Mechanical")
        customers.add("Civil")
        customers.add("Interior")
        customers.add("Account")
        return customers
    }

    private fun initUIOccupation() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.SelectOccupationTextView)

        // create list of customer
        val customerList = getOccupationList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getOccupationList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("TCS")
        customers.add("L&T")
        customers.add("Roongtha")
        customers.add("RIO")
        customers.add("Global")
        return customers
    }

    private fun initUIIncome() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.SelectIncomeTextView)

        // create list of customer
        val customerList = getIncomeList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getIncomeList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("10000")
        customers.add("20000")
        customers.add("50000")
        customers.add("80000")
        customers.add("500000")
        return customers
    }

    private fun initUIMaritalStatus() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.maritalStatusTextView)

        // create list of customer
        val customerList = getMaritalStatusList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getMaritalStatusList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("Married")
        customers.add("Unmarried")
        customers.add("Divosed")
        return customers
    }

    private fun initUIMotherTongue() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.motherTonugeTextView)

        // create list of customer
        val customerList = getMotherTongueList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getMotherTongueList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("Marathi")
        customers.add("Hindi")
        customers.add("Gujarati")
        customers.add("Panjabi")
        customers.add("Tamil")
        return customers
    }

    private fun initUIReligion() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.religionTextView)

        // create list of customer
        val customerList = getSelectReligionList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getSelectReligionList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("Hindu")
        customers.add("Muslim")
        customers.add("Sikh")
        return customers
    }

    private fun initUICast() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.castTextView)

        // create list of customer
        val customerList = getCastList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getCastList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("General")
        customers.add("OBC")
        customers.add("ST")
        customers.add("Other")
        return customers
    }

    private fun initUIHoroscope() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.horoscopeTextView)

        // create list of customer
        val customerList = getHoroscopeList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)
    }

    private fun getHoroscopeList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("Aries")
        customers.add("Gemini")
        customers.add("Taurus")
        return customers
    }

    private fun initUIManglik() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.ManglikTextView)

        // create list of customer
        val customerList = getManglikList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getManglikList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("Yes")
        customers.add("No")
        return customers
    }

    private fun initUIFathersStatus() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.fatherStatusTextView)

        // create list of customer
        val customerList = getFathersStatusList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getFathersStatusList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("Married")
        customers.add("Unmarried")
        customers.add("Divorced")
        return customers
    }

    private fun initUIMothersStatus() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.motherStatusTextView)

        // create list of customer
        val customerList = getMothersStatusList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getMothersStatusList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("Married")
        customers.add("Unmarried")
        customers.add("Divorced")
        return customers
    }

    private fun initUIBrother() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.brotherTextView)

        // create list of customer
        val customerList = getBrotherList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getBrotherList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("1")
        customers.add("2")
        customers.add("3")
        return customers
    }

    private fun initUINoofMarriedBrother() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.brotherMarriedTextView)

        // create list of customer
        val customerList = getBrotherMarriedList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

        //submit button click event registration
//        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(Personal_Information.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private fun getBrotherMarriedList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("1")
        customers.add("2")
        customers.add("3")
        return customers
    }

    private fun initUISister() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.sisterTextView)

        // create list of customer
        val customerList = getSisterList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)

 
    }

    private fun getSisterList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("1")
        customers.add("2")
        customers.add("3")
        return customers
    }

    private fun initUINoofMarriedSister() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.sisterMarriedTextView)

        // create list of customer
        val customerList = getSisterMarriedList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoTV.setAdapter(adapter)
        
    }

    private fun getSisterMarriedList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("1")
        customers.add("2")
        customers.add("3")
        return customers
    }


}