package com.example.matrimony.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.matrimony.R
import com.example.matrimony.fragment.FamilyDetaisFragment
import com.example.matrimony.fragment.PersonalDetailsFragment
import com.example.matrimony.model.ProfileResponse
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ShowDetails : AppCompatActivity() {
    private val toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var pr_bar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        pr_bar = findViewById(R.id.pr_bar)
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
                                Utils.Gender = responseBody.userDetails!!.gender.toString()
                                Utils.BirthDate = responseBody.userprofileDetails!!.birth_date.toString()
                                Utils.MarriedStatus = responseBody.userprofileDetails!!.married_status.toString()
                                Utils.CityName = responseBody.cityDetails!!.name.toString()
                                Utils.StateName = responseBody.stateDetails!!.name.toString()
                                Utils.religionName = responseBody.religionDetails!!.name.toString()
                                Utils.casteName = responseBody.casteDetails!!.name.toString()
                                Utils.mothertoungeName = responseBody.motherToungeDetails!!.get(0).name.toString()
                                Utils.educationName = responseBody.educationDetails!!.name.toString()
                                Utils.occupationName = responseBody.occuoatonDetails!!.name.toString()
                                Utils.incomeDetails =responseBody.userprofileDetails!!.annual_income.toString()
                                Utils.famincomeDetails =responseBody.userprofileDetails!!.family_income.toString()
                                Utils.foccuDetails =responseBody.fatheroccupatonDetails!!.name.toString()
                                Utils.moccuDetails =responseBody.motheroccupatonDetails!!.name.toString()

                                Utils.user_bio =responseBody.userprofileDetails!!.user_bio.toString()
                                Utils.contact_no =responseBody.userDetails!!.mobile.toString()
                                Utils.email_id =responseBody.userDetails!!.email.toString()

                                Utils.noofbro =responseBody.userprofileDetails!!.brother_no.toString()
                                Utils.noofmarrbro =responseBody.userprofileDetails!!.brother_married_no.toString()
                                Utils.noofsis =responseBody.userprofileDetails!!.sister_no.toString()
                                Utils.noofmarrsis =responseBody.userprofileDetails!!.sister_married_no.toString()

                                Utils.AboutFamily =responseBody.userprofileDetails!!.about_family.toString()
                                pr_bar!!.visibility =View.GONE

                                setPages()

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