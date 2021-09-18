package com.example.matrimony.activity

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.matrimony.R
import com.example.matrimony.fragment.PersonalDetailsFragment
import com.example.matrimony.fragment.FamilyDetaisFragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.matrimony.fragment.PreferenceFragment
import java.util.ArrayList

class MenDetailsShow : AppCompatActivity() {
    private val toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_men_details_show)
        viewPager = findViewById<View>(R.id.viewpager_Men) as ViewPager
        setupViewPager(viewPager)
        tabLayout = findViewById<View>(R.id.tabs_Men) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter: ViewPagerAdapter = ViewPagerAdapter(
            supportFragmentManager
        )
        //add fragments
        adapter.addFragment(PersonalDetailsFragment(), "Personal")
        adapter.addFragment(FamilyDetaisFragment(), "Family")
        adapter.addFragment(PreferenceFragment(), "Preference")
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