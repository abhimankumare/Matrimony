package com.example.matrimony.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimony.R
import com.example.matrimony.adapter.SelectionAdapter
import java.util.*


lateinit var ll_career_details: LinearLayout
lateinit var ll_personal_details: LinearLayout
lateinit var ll_social_details: LinearLayout
lateinit var ll_family_details: LinearLayout
lateinit var rv_selection: RecyclerView
private lateinit var selectionAdapter: SelectionAdapter
lateinit var toolbar1: Toolbar


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ll_career_details = findViewById(R.id.ll_career_details)
        ll_personal_details = findViewById(R.id.ll_personal_details)
        ll_social_details = findViewById(R.id.ll_social_details)
        ll_family_details = findViewById(R.id.ll_family_details)
        rv_selection = findViewById(R.id.rv_selection)



        toolbar1 = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar1)
        getSupportActionBar()!!.setTitle("Details");



        //Set Adpater
        setSelfAdapter()


        //Personal Details
        initUIHeight()
        initUICountry()
        initUIState()
        initUICity()
        //Career Details
        initUIEducatoin()
        initUISector()
        initUIOccupation()
        initUIIncome()
        //Social Details
        initUIMaritalStatus()
        initUIMotherTongue()
        initUIReligion()
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

        val save = findViewById<View>(R.id.saveButton) as Button
        save.setOnClickListener {
            ll_personal_details.visibility = View.GONE
            ll_career_details.visibility = View.VISIBLE
            getSupportActionBar()!!.setTitle("Career Details");
        }

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

        // create list of customer
        val customerList = getCustomerHeightList()

        //Create adapter
        val adapter = ArrayAdapter(
            this@RegisterActivity,
            android.R.layout.simple_spinner_item,
            customerList
        )

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

    private fun initUICountry() {
        run {

            //UI reference of textView
            val customerAutoTV =
                findViewById<AutoCompleteTextView>(R.id.customerCountryTextView)

            // create list of customer
            val customerList = getCustomerCountryList()

            //Create adapter
            val adapter = ArrayAdapter(
                this@RegisterActivity,
                android.R.layout.simple_spinner_item,
                customerList
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

            // create list of customer
            val customerList = getCustomerStateList()

            //Create adapter
            val adapter = ArrayAdapter(
                this@RegisterActivity,
                android.R.layout.simple_spinner_item,
                customerList
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

            // create list of customer
            val customerList = getCustomerCityList()

            //Create adapter
            val adapter = ArrayAdapter(
                this@RegisterActivity,
                android.R.layout.simple_spinner_item,
                customerList
            )

            //Set adapter
            customerAutoTV.setAdapter(adapter)
        }
    }

    private fun getCustomerHeightList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("4' 6" + "(137 cm)")
        customers.add("4' 7" + "(140 cm)")
        customers.add("4' 8" + "(142 cm)")
        customers.add("4' 9" + "(145 cm)")
        customers.add("4' 10" + "(147 cm)")
        customers.add("4' 11" + "(150 cm)")
        customers.add("5' 0" + "(152 cm)")
        customers.add("5' 1" + "(155 cm)")
        customers.add("5' 2" + "(157 cm)")
        customers.add("5' 3" + "(160 cm)")
        customers.add("5' 4" + "(163 cm)")
        customers.add("5' 5" + "(165 cm)")
        return customers
    }

    private fun getCustomerCountryList(): ArrayList<String> {
        val Country = ArrayList<String>()
        Country.add("India")
        Country.add("America")
        Country.add("Africa")
        Country.add("Dubai")
        Country.add("Italy")
        return Country
    }

    private fun getCustomerStateList(): ArrayList<String> {
        val Country = ArrayList<String>()
        Country.add("MAHARASTRA")
        Country.add("GUJRAT")
        Country.add("PUNJAB")
        Country.add("RAJASHTAN")
        Country.add("DELHI")
        return Country
    }

    private fun getCustomerCityList(): ArrayList<String> {
        val Country = ArrayList<String>()
        Country.add("NASHIK")
        Country.add("PUNE")
        Country.add("MUMBAI")
        Country.add("KOKAN")
        Country.add("NAGPUR")
        return Country
    }

    private fun initUIEducatoin() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.educationTextView)

        // create list of customer
        val customerList = getEducationList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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

    private fun getEducationList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("BSC")
        customers.add("BSC(Comp.Sci.)")
        customers.add("BCOM")
        customers.add("BBA")
        customers.add("BA")
        return customers
    }

    private fun initUISector() {
        //UI reference of textView
        val customerAutoTV = findViewById<AutoCompleteTextView>(R.id.selectSectorTextView)

        // create list of customer
        val customerList = getSectorList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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
            ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, customerList)

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