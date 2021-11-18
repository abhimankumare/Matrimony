package com.example.matrimony.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.matrimony.R
import com.example.matrimony.fragment.FamilyDetaisFragment
import com.example.matrimony.fragment.PersonalDetailsFragment
import com.example.matrimony.model.MasterContent
import com.example.matrimony.model.MasterResponse
import com.example.matrimony.model.ProfileResponse
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ShowDetails : AppCompatActivity() {
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var pr_bar: ProgressBar? = null
    lateinit var iv_profilepic: ImageView
    lateinit var user_name: TextView
    lateinit var tv_heightyear: TextView
    lateinit var tv_citystate: TextView
    lateinit var tv_casteset: TextView
    lateinit var tv_langua: TextView
    lateinit var tv_income: TextView
    lateinit var tv_marr: TextView
    lateinit var toolbar: Toolbar
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        pr_bar = findViewById(R.id.pr_bar)



        iv_profilepic = findViewById(R.id.iv_profilepic)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setTitle("Profile Details")


        user_name = findViewById(R.id.user_name)
        tv_heightyear = findViewById(R.id.tv_heightyear)
        tv_citystate = findViewById(R.id.tv_citystate)
        tv_casteset = findViewById(R.id.tv_casteset)
        tv_langua = findViewById(R.id.tv_langua)
        tv_income = findViewById(R.id.tv_income)
        tv_marr = findViewById(R.id.tv_marr)

//        val imgFile = File(Utils.urifile)
//
//        if (imgFile.exists()) {
//            val myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
//            iv_profilepic.setImageBitmap(myBitmap)
//        }

        Glide.with(this@ShowDetails).load(Utils.urifile).into(iv_profilepic);

        getProfileData()


    }

    fun getProfileData() {
        try {
            if (Utils.isConnectingToInternet(this@ShowDetails)) {
                pr_bar!!.visibility =View.VISIBLE
                val retIn = ApiInterface.RetrofitInstance.getRetrofitInstance()
                    .create(ApiInterface::class.java)
                retIn.ProfileData().enqueue(object : Callback<ProfileResponse> {
                    override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                        println("In Data Failure")
                    }

                    override fun onResponse(
                        call: Call<ProfileResponse>,
                        response: Response<ProfileResponse>
                    ) {
                        if (response.code() == 200) {
                            //  progressBar.visibility=View.VISIBLE
                            val responseBody: ProfileResponse? = response.body()
                            if (responseBody != null) {
                                println("In Data "+responseBody.toString())
                                Utils.UserName = responseBody.userDetails!!.name.toString()
                                user_name.setText(responseBody.userDetails!!.name.toString())
                                Utils.Gender = responseBody.userDetails!!.gender.toString()
                                Utils.BirthDate = responseBody.userprofileDetails!!.birth_date.toString()
                                Utils.MarriedStatus = responseBody.userprofileDetails!!.married_status.toString()
                                Utils.CityName = responseBody.cityDetails!!.name.toString()
                                Utils.StateName = responseBody.stateDetails!!.name.toString()
                                tv_citystate.setText(responseBody.cityDetails!!.name.toString()
                                        +","+responseBody.stateDetails!!.name.toString())
                                if(responseBody.religionDetails != null) {
                                    Utils.religionName =
                                        responseBody.religionDetails!!.name.toString()

                                    religion_id = responseBody.userprofileDetails!!.religion_id?.toString()
                                    caste_id = responseBody.userprofileDetails!!.caste_id?.toString()
                                    Utils.casteName = responseBody.casteDetails!!.name.toString()
                                    tv_casteset.setText(
                                        responseBody.religionDetails!!.name.toString()
                                                + "," + responseBody.casteDetails!!.name.toString()
                                    )
                                }
                                if(responseBody.motherToungeDetails.size > 0) {
                                    Utils.mothertoungeName =
                                        responseBody.motherToungeDetails!!.get(0).name.toString()
                                    mothertounge_id =  responseBody.userprofileDetails!!.language_id?.toString()
                                    tv_langua.setText(responseBody.motherToungeDetails!!.get(0).name.toString())
                                }
                                if(responseBody.educationDetails != null) {
                                    education_id = responseBody.userprofileDetails!!.educations_id?.toString()
                                    Utils.educationName =
                                        responseBody.educationDetails!!.name.toString()
                                }
                                if(responseBody.occuoatonDetails != null) {
                                    occupation_id = responseBody.userprofileDetails!!.occupation_id?.toString()
                                    Utils.occupationName =
                                        responseBody.occuoatonDetails!!.name.toString()
                                }
                                if(responseBody.userprofileDetails!!.annual_income != null) {
                                    Utils.incomeDetails =
                                        responseBody.userprofileDetails!!.annual_income.toString()
                                }
                                if(responseBody.userprofileDetails!!.family_income != null) {
                                    Utils.famincomeDetails =
                                        responseBody.userprofileDetails!!.family_income.toString()
                                }
                                if(responseBody.fatheroccupatonDetails!= null) {
                                    occupation_id_father = responseBody.userprofileDetails!!.father_occupation_id?.toString()
                                    Utils.foccuDetails =responseBody.fatheroccupatonDetails!!.name.toString()
                                }
                                if(responseBody.motheroccupatonDetails!= null) {
                                    Utils.moccuDetails =responseBody.motheroccupatonDetails!!.name.toString()
                                    occupation_id_mother = responseBody.userprofileDetails!!.mother_occupation_id?.toString()
                                    tv_income.setText(responseBody.userprofileDetails!!.annual_income.toString())
                                }

                                Utils.user_type = responseBody.userprofileDetails!!.user_type.toString()
                                Utils.user_bio =responseBody.userprofileDetails!!.user_bio.toString()
                                Utils.contact_no =responseBody.userDetails!!.mobile.toString()
                                Utils.email_id =responseBody.userDetails!!.email.toString()
                                Utils.marstatus = responseBody.userprofileDetails!!.married_status.toString()
                                tv_marr.setText(responseBody.userprofileDetails!!.married_status.toString())
                                Utils.noofbro =responseBody.userprofileDetails!!.brother_no.toString()
                                Utils.noofmarrbro =responseBody.userprofileDetails!!.brother_married_no.toString()
                                Utils.noofsis =responseBody.userprofileDetails!!.sister_no.toString()
                                Utils.noofmarrsis =responseBody.userprofileDetails!!.sister_married_no.toString()

                                if(responseBody.userprofileDetails!!.about_family!= null) {
                                    Utils.AboutFamily =
                                        responseBody.userprofileDetails!!.about_family.toString()
                                }

                                if(responseBody.userprofileDetails!!.employed_sector!= null) {
                                    Utils.employe_sector =
                                        responseBody.userprofileDetails!!.employed_sector.toString()
                                }
                                if(responseBody.userprofileDetails!!.horoscope!= null) {
                                    Utils.horoscope =
                                        responseBody.userprofileDetails!!.horoscope.toString()
                                }
                                if(responseBody.userprofileDetails!!.manglik!= null) {
                                    Utils.manglik =
                                        responseBody.userprofileDetails!!.manglik.toString()
                                }


                                pr_bar!!.visibility =View.GONE

                                setPages()

                                getMasterData()

                            }
                        }else{
                            pr_bar!!.visibility =View.GONE
                            //progressBar.visibility=View.GONE
                        }
                    }
                })
            }else{
            }
        } catch (err: Exception) {
            println("In Data catch")
            err.printStackTrace()
        }
    }

    fun getMasterData() {
        try {
            if (Utils.isConnectingToInternet(this@ShowDetails)) {
                val retIn = ApiInterface.RetrofitInstanceNew.getRetrofitInstanceNew()
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
                                listemployed_sector.clear()
                                listeducation.clear()
                                listoccupation.clear()
                                listincome.clear()
                                listmother_tongue.clear()
                                listreligion.clear()
                                listcaste.clear()
                                listhoroscope.clear()
                                listLanguage.clear()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menuedit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_edit_profile -> {
                val intent = Intent(this@ShowDetails, BasicDataActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.putExtra("fromData","ShowDetails")
                startActivity(intent)
                return true
            }

        }
        return actionBarDrawerToggle!!.onOptionsItemSelected(item)
    }

    private fun setPages() {
        viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        setupViewPager(viewPager)
        tabLayout = findViewById<View>(R.id.tabs) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        //add fragments
        adapter.addFragment(PersonalDetailsFragment(), "Personal")
        adapter.addFragment(FamilyDetaisFragment(), "Family")
        //adapter.addFragment(PreferenceFragment(), "Preference")
        viewPager!!.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager?) : FragmentPagerAdapter(
        manager!!
    ) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}