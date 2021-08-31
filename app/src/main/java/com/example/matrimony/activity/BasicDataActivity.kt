package com.example.matrimony.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.LinearLayout
import com.example.matrimony.R
import java.util.ArrayList


lateinit var ll_career_details: LinearLayout
lateinit var ll_social_details: LinearLayout
lateinit var ll_family_details: LinearLayout

lateinit var educationTextView: AutoCompleteTextView
lateinit var selectSectorTextView: AutoCompleteTextView
lateinit var SelectOccupationTextView: AutoCompleteTextView
lateinit var SelectIncomeTextView: AutoCompleteTextView
lateinit var maritalStatusTextView: AutoCompleteTextView

lateinit var motherTonugeTextView: AutoCompleteTextView
lateinit var religionTextView: AutoCompleteTextView
lateinit var castTextView: AutoCompleteTextView
lateinit var horoscopeTextView: AutoCompleteTextView
lateinit var ManglikTextView: AutoCompleteTextView

lateinit var fatherStatusTextView: AutoCompleteTextView
lateinit var motherStatusTextView: AutoCompleteTextView
lateinit var brotherTextView: AutoCompleteTextView
lateinit var brotherMarriedTextView: AutoCompleteTextView
lateinit var sisterTextView: AutoCompleteTextView
lateinit var sisterMarriedTextView: AutoCompleteTextView


class BasicDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_data)


        ll_career_details = findViewById(R.id.ll_career_details)
        ll_social_details = findViewById(R.id.ll_social_details)
        ll_family_details = findViewById(R.id.ll_family_details)

        toolbar1 = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar1)
        getSupportActionBar()!!.setTitle("Career Details");


        initUIEducatoin()
        initUISector()
        initUIOccupation()
        initUIReligion()

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
                val intent = Intent(this@BasicDataActivity, MainActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            } catch (err: Exception) {
                err.printStackTrace()
            }
        }
    }


    private fun initUIEducatoin() {
        //UI reference of textView
        val customerAutoEducation = findViewById<AutoCompleteTextView>(R.id.educationTextView)
        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, spinnereducationArray)

        //Set adapter
        customerAutoEducation.setAdapter(adapter)

        customerAutoEducation.setOnItemClickListener { parent, view, position, id ->
            education_id = spinnereducationArrayIds.get(position)
        }

        //submit button click event registration

    }


    private fun initUISector() {
        //UI reference of textView
        val customerAutoSector = findViewById<AutoCompleteTextView>(R.id.selectSectorTextView)
        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, spinneremployed_sectorArray)

        //Set adapter
        customerAutoSector.setAdapter(adapter)
        customerAutoSector.setOnItemClickListener { parent, view, position, id ->
            sector_id = spinneremployed_sectorArrayIds.get(position)
        }

    }

    private fun initUIOccupation() {
        //UI reference of textView
        val customerAutoOccupation = findViewById<AutoCompleteTextView>(R.id.SelectOccupationTextView)
        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, spinneroccupationArray)
        //Set adapter
        customerAutoOccupation.setAdapter(adapter)

        customerAutoOccupation.setOnItemClickListener { parent, view, position, id ->
            occupation_id = spinneroccupationArrayIds.get(position)
        }
    }

    private fun initUIReligion() {
        //UI reference of textView
        val customerAutoReligion = findViewById<AutoCompleteTextView>(R.id.religionTextView)

        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, spinnerreligionArray)

        //Set adapter
        customerAutoReligion.setAdapter(adapter)
        customerAutoReligion.setOnItemClickListener { parent, view, position, id ->
            religion_id = spinnerreligionArrayIds.get(position)
        }
    }

    private fun initUIIncome() {
        //UI reference of textView
        val customerAutoIncome = findViewById<AutoCompleteTextView>(R.id.SelectIncomeTextView)
        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, spinnerincomeArray)

        //Set adapter
        customerAutoIncome.setAdapter(adapter)

        customerAutoIncome.setOnItemClickListener { parent, view, position, id ->
            income_id = spinnerincomeArrayIds.get(position)
        }
    }

    private fun initUIMaritalStatus() {
        //UI reference of textView
        val customerMarriedStatus = findViewById<AutoCompleteTextView>(R.id.maritalStatusTextView)

        // create list of customer
        val customerList = getMaritalStatusList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerMarriedStatus.setAdapter(adapter)
    }

    private fun getMaritalStatusList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("Married")
        customers.add("Unmarried")
        customers.add("Divorced")
        return customers
    }

    private fun initUIMotherTongue() {
        //UI reference of textView
        val customerAutoMotheTounge = findViewById<AutoCompleteTextView>(R.id.motherTonugeTextView)
        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, spinnermother_tongueArray)

        //Set adapter
        customerAutoMotheTounge.setAdapter(adapter)

        customerAutoMotheTounge.setOnItemClickListener { parent, view, position, id ->
            mothertounge_id = spinnermother_tongueArrayIds.get(position)
        }
    }

    private fun initUICast() {
        //UI reference of textView
        val customerAutoCaste = findViewById<AutoCompleteTextView>(R.id.castTextView)


        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, spinnercasteArray)

        //Set adapter
        customerAutoCaste.setAdapter(adapter)

        customerAutoCaste.setOnItemClickListener { parent, view, position, id ->
            caste_id = spinnercasteArrayIds.get(position)
        }
    }

    private fun initUIHoroscope() {
        //UI reference of textView
        val customerAutoHoroscope = findViewById<AutoCompleteTextView>(R.id.horoscopeTextView)
        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, spinnerhoroscopeArray)

        //Set adapter
        customerAutoHoroscope.setAdapter(adapter)

        customerAutoHoroscope.setOnItemClickListener { parent, view, position, id ->
            horoscope_id = spinnerhoroscopeArrayIds.get(position)
        }
    }


    private fun initUIManglik() {
        //UI reference of textView
        val customerAutoManglik = findViewById<AutoCompleteTextView>(R.id.ManglikTextView)

        // create list of customer
        val customerList = getManglikList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoManglik.setAdapter(adapter)
    }

    private fun getManglikList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("Yes")
        customers.add("No")
        return customers
    }

    private fun initUIFathersStatus() {
        //UI reference of textView
        val customerAutoFathersStatus = findViewById<AutoCompleteTextView>(R.id.fatherStatusTextView)

        // create list of customer
        val customerList = getFathersStatusList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoFathersStatus.setAdapter(adapter)

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
        val customerAutoMothersStatus = findViewById<AutoCompleteTextView>(R.id.motherStatusTextView)

        // create list of customer
        val customerList = getMothersStatusList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoMothersStatus.setAdapter(adapter)

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
        val customerAutoBrother = findViewById<AutoCompleteTextView>(R.id.brotherTextView)

        // create list of customer
        val customerList = getBrotherList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoBrother.setAdapter(adapter)
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
        val customerAutoMarriedBrother = findViewById<AutoCompleteTextView>(R.id.brotherMarriedTextView)

        // create list of customer
        val customerList = getBrotherMarriedList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoMarriedBrother.setAdapter(adapter)
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
        val customerAutoSister = findViewById<AutoCompleteTextView>(R.id.sisterTextView)

        // create list of customer
        val customerList = getSisterList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoSister.setAdapter(adapter)


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
        val customerAutoMarriedSister = findViewById<AutoCompleteTextView>(R.id.sisterMarriedTextView)

        // create list of customer
        val customerList = getSisterMarriedList()

        //Create adapter
        val adapter =
            ArrayAdapter(this@BasicDataActivity, R.layout.custome_new_spinner, customerList)

        //Set adapter
        customerAutoMarriedSister.setAdapter(adapter)

    }

    private fun getSisterMarriedList(): ArrayList<String> {
        val customers = ArrayList<String>()
        customers.add("1")
        customers.add("2")
        customers.add("3")
        return customers
    }

}