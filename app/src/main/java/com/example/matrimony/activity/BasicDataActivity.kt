package com.example.matrimony.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.matrimony.R
import com.example.matrimony.model.BasicData
import com.example.matrimony.model.SignUpResponse
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

var occupation_id_father: String? = null
var occupation_id_mother: String? = null

class BasicDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_data)


        ll_career_details = findViewById(R.id.ll_career_details)
        ll_social_details = findViewById(R.id.ll_social_details)
        ll_family_details = findViewById(R.id.ll_family_details)


        educationTextView = findViewById(R.id.educationTextView)
        selectSectorTextView = findViewById(R.id.selectSectorTextView)
        SelectOccupationTextView = findViewById(R.id.SelectOccupationTextView)
        SelectIncomeTextView = findViewById(R.id.SelectIncomeTextView)
        maritalStatusTextView = findViewById(R.id.maritalStatusTextView)


        motherTonugeTextView = findViewById(R.id.motherTonugeTextView)
        religionTextView = findViewById(R.id.religionTextView)
        castTextView = findViewById(R.id.castTextView)
        horoscopeTextView = findViewById(R.id.horoscopeTextView)
        ManglikTextView = findViewById(R.id.ManglikTextView)


        fatherStatusTextView = findViewById(R.id.fatherStatusTextView)
        motherStatusTextView = findViewById(R.id.motherStatusTextView)
        brotherTextView = findViewById(R.id.brotherTextView)
        brotherMarriedTextView = findViewById(R.id.brotherMarriedTextView)
        sisterTextView = findViewById(R.id.sisterTextView)
        sisterMarriedTextView = findViewById(R.id.sisterMarriedTextView)

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
            try {
                if (educationTextView.text.isNullOrBlank()) {
                    toast("Please Select Education")
                } else if (selectSectorTextView.text.isNullOrBlank()) {
                    toast("Please Select Sector")
                } else if (SelectOccupationTextView.text.isNullOrBlank()) {
                    toast("Please Select Occupation")
                } else if (SelectIncomeTextView.text.isNullOrBlank()) {
                    toast("Please Select Income")
                } else {
                    ll_career_details.visibility = View.GONE
                    ll_social_details.visibility = View.VISIBLE
                    getSupportActionBar()!!.setTitle("Social Details");
                }
            } catch (err: Exception) {
                err.printStackTrace()
            }


        }

        val social_save = findViewById<View>(R.id.social_save) as Button
        social_save.setOnClickListener {
            try {
                if (maritalStatusTextView.text.isNullOrBlank()) {
                    toast("Please Select Marital Status")
                } else if (motherTonugeTextView.text.isNullOrBlank()) {
                    toast("Please Select Mother Tounge")
                } else if (religionTextView.text.isNullOrBlank()) {
                    toast("Please Select Religion")
                } else if (castTextView.text.isNullOrBlank()) {
                    toast("Please Select Caste")
                } else if (horoscopeTextView.text.isNullOrBlank()) {
                    toast("Please Select Horoscope")
                } else if (ManglikTextView.text.isNullOrBlank()) {
                    toast("Please Select Magalik Status")
                } else {
                    ll_social_details.visibility = View.GONE
                    ll_family_details.visibility = View.VISIBLE
                    getSupportActionBar()!!.setTitle("Family Details");
                }
            } catch (err: Exception) {
                err.printStackTrace()
            }


        }

        val save_family_details = findViewById<View>(R.id.save_family_details) as Button
        save_family_details.setOnClickListener {
            try {
                if (fatherStatusTextView.text.isNullOrBlank()) {
                    toast("Please Select Fathers Status")
                } else if (motherStatusTextView.text.isNullOrBlank()) {
                    toast("Please Select Mother Status")
                } else if (brotherTextView.text.isNullOrBlank()) {
                    toast("Please Select Nu of Brothers")
                } else if (brotherMarriedTextView.text.isNullOrBlank()) {
                    toast("Please Select Married Brothers")
                } else if (sisterTextView.text.isNullOrBlank()) {
                    toast("Please Select Nu of Sisters")
                } else if (sisterMarriedTextView.text.isNullOrBlank()) {
                    toast("Please Select Married Sisters")
                } else {
                    RegisterBasicDataOfUser(
                        education_id,
                        sector_id,
                        occupation_id,
                        SelectIncomeTextView.text.toString(),
                        maritalStatusTextView.text.toString(),
                        mothertounge_id,
                        religion_id,
                        caste_id,
                        horoscopeTextView.text.toString(),
                        ManglikTextView.text.toString(),
                        occupation_id_father,
                        occupation_id_mother,
                        brotherTextView.text.toString(),
                        sisterTextView.text.toString(),
                        brotherMarriedTextView.text.toString(),
                        sisterMarriedTextView.text.toString(),
                        "1"
                    )
                }


//                val intent = Intent(this@BasicDataActivity, MainActivity::class.java)
//                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                startActivity(intent)
//                finish()
            } catch (err: Exception) {
                err.printStackTrace()
            }
        }
    }

    override fun onBackPressed() {
        Utils.showDialog(
            "Do You Want to Exit From App? Your Data Will Be Lost",
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        dialog.dismiss()
                        val intent = Intent(this@BasicDataActivity, LoginActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()

                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        dialog.dismiss()
                    }
                }
            }, this
        )

    }
    private fun RegisterBasicDataOfUser(
        educations_id: String?,
        employed_sector: String?,
        occupation_id: String?,
        annual_income: String?,
        married_status: String,
        language_id: String?,
        religion_id: String?,
        caste_id: String?,
        horoscope: String?,
        manglik: String,
        father_occupation_id: String?,
        mother_occupation_id: String?,
        brother_no: String,
        sister_no: String,
        brother_married_no: String,
        sister_married_no: String,
        open_cast_marriage: String?
    ) {

        if (Utils.isConnectingToInternet(this)) {
            val retIn =
                ApiInterface.RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
            val basicDataInfo = BasicData(
                educations_id,
                employed_sector,
                occupation_id,
                annual_income,
                married_status,
                language_id,
                religion_id,
                caste_id,
                horoscope,
                manglik,
                father_occupation_id,
                mother_occupation_id,
                brother_no,
                sister_no,
                brother_married_no,
                sister_married_no,
                open_cast_marriage
            )
            retIn.RegisterBasicData(basicDataInfo).enqueue(object : Callback<SignUpResponse> {
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    toast(t.message.toString())
                }

                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    if (response.code() == 200) {
                        responseBody = response.body()
//                    if (responseBody != null) {
//                        token = responseBody!!.token
//                        Utils.token = responseBody!!.token.toString()
//                    }

                        toast("Profile added successfull..")
                        gotoMainActivity()
                        //   {"status":{"status":"success"},"message":{"message":"Profile added successfull.."}}


                    } else {
                        //  Toast.makeText(this@BasicDataActiviy, responseBody!!.error.toString(), Toast.LENGTH_SHORT)
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

    private fun gotoMainActivity() {

        val intent = Intent(this@BasicDataActivity, MainActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()

    }


    private fun initUIEducatoin() {
        //UI reference of textView
        val customerAutoEducation = findViewById<AutoCompleteTextView>(R.id.educationTextView)
        //Create adapter
        val adapter =
            ArrayAdapter(
                this@BasicDataActivity,
                R.layout.custome_new_spinner,
                spinnereducationArray
            )

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
            ArrayAdapter(
                this@BasicDataActivity,
                R.layout.custome_new_spinner,
                spinneremployed_sectorArray
            )

        //Set adapter
        customerAutoSector.setAdapter(adapter)
        customerAutoSector.setOnItemClickListener { parent, view, position, id ->
            sector_id = spinneremployed_sectorArrayIds.get(position)
        }

    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initUIOccupation() {
        //UI reference of textView
        val customerAutoOccupation =
            findViewById<AutoCompleteTextView>(R.id.SelectOccupationTextView)
        //Create adapter
        val adapter =
            ArrayAdapter(
                this@BasicDataActivity,
                R.layout.custome_new_spinner,
                spinneroccupationArray
            )
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
            ArrayAdapter(
                this@BasicDataActivity,
                R.layout.custome_new_spinner,
                spinnermother_tongueArray
            )

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
            ArrayAdapter(
                this@BasicDataActivity,
                R.layout.custome_new_spinner,
                spinnerhoroscopeArray
            )

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
        val customerAutoFathersStatus =
            findViewById<AutoCompleteTextView>(R.id.fatherStatusTextView)


        //Create adapter
        val adapter =
            ArrayAdapter(
                this@BasicDataActivity,
                R.layout.custome_new_spinner,
                spinneroccupationArray
            )

        //Set adapter
        customerAutoFathersStatus.setAdapter(adapter)

        customerAutoFathersStatus.setOnItemClickListener { parent, view, position, id ->
            occupation_id_father = spinneroccupationArrayIds.get(position)
        }


    }


    private fun initUIMothersStatus() {
        //UI reference of textView
        val customerAutoMothersStatus =
            findViewById<AutoCompleteTextView>(R.id.motherStatusTextView)
        //Create adapter
        val adapter =
            ArrayAdapter(
                this@BasicDataActivity,
                R.layout.custome_new_spinner,
                spinneroccupationArray
            )

        //Set adapter
        customerAutoMothersStatus.setAdapter(adapter)

        customerAutoMothersStatus.setOnItemClickListener { parent, view, position, id ->
            occupation_id_mother = spinneroccupationArrayIds.get(position)
        }

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
        customers.add("0")
        customers.add("1")
        customers.add("2")
        customers.add("3")
        return customers
    }

    private fun initUINoofMarriedBrother() {
        //UI reference of textView
        val customerAutoMarriedBrother =
            findViewById<AutoCompleteTextView>(R.id.brotherMarriedTextView)

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
        
        customers.add("0")
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
        customers.add("0")
        customers.add("1")
        customers.add("2")
        customers.add("3")
        return customers
    }

    private fun initUINoofMarriedSister() {
        //UI reference of textView
        val customerAutoMarriedSister =
            findViewById<AutoCompleteTextView>(R.id.sisterMarriedTextView)

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
        customers.add("0")
        customers.add("1")
        customers.add("2")
        customers.add("3")
        return customers
    }

}