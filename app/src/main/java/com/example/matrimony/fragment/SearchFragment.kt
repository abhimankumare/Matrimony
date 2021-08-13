package com.example.matrimony.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.matrimony.R

import com.example.matrimony.adapter.MyAdapter
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

lateinit var vp_home_detail: ViewPager
lateinit var tl_home_detail_bar: TabLayout

class SearchFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    internal lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Search"
        init(view)
        return view
    }

    private fun init(view: View) {

        vp_home_detail = view.findViewById<ViewPager>(R.id.viewPager)

        tl_home_detail_bar = view.findViewById<TabLayout>(R.id.tabLayout)

        tl_home_detail_bar.addTab(tl_home_detail_bar.newTab().setText("Regular Search"))
        tl_home_detail_bar.addTab(tl_home_detail_bar.newTab().setText("Matrimony ID"))

        tl_home_detail_bar.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(
            requireContext(), childFragmentManager,
            tl_home_detail_bar.tabCount
        )
        vp_home_detail.adapter = adapter
        vp_home_detail.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_home_detail_bar))
        tl_home_detail_bar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                vp_home_detail.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }



    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}