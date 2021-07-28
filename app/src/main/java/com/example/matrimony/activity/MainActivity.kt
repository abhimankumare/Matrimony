package com.example.matrimony.activity

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.matrimony.R
import com.example.poultry_i.common.Utils.BlurBuilder.blur
import com.example.poultry_i.fragment.HomeFragment
import com.google.android.material.navigation.NavigationView
import java.util.*


class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var imageView: ImageView
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        assert(navView != null)
        navView.setNavigationItemSelectedListener(this@MainActivity)
        val headerView: View = navView.getHeaderView(0)

        imageView = headerView.findViewById(R.id.imageView)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.hamburgerdr)
        val blurredBitmap = blur(this@MainActivity, bitmap)
        imageView.setBackgroundDrawable(BitmapDrawable(resources, blurredBitmap))

    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.frame_container, fragment, tag)
        ft.commitAllowingStateLoss()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
//            R.id.action_noification -> {
//                addFragment(
//                    NotificationFragment(),
//                    true,
//                    NotificationFragment::class.java.simpleName
//                )
//                return true
//            }

        }
        return actionBarDrawerToggle!!.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> {
                addFragment(
                    HomeFragment(),
                    true,
                    HomeFragment::class.java.simpleName
                )
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
//            R.id.nav_logout -> {
//                Utils.showDialog(
//                    "Are you sure you want to logout?",
//                    DialogInterface.OnClickListener { dialog, which ->
//                        when (which) {
//                            DialogInterface.BUTTON_POSITIVE -> {
//                                dialog.dismiss()
//                              //  Utils.logoutclearperf(this@MainActivity)
//                            }
//                            DialogInterface.BUTTON_NEGATIVE -> {
//                                dialog.dismiss()
//                            }
//                        }
//                    }, this
//                )
//                return true
//            }
        }
        return actionBarDrawerToggle!!.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
           // bottomNavigationView.selectedItemId = R.id.action_home
        } else if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        } else {
            finishAffinity()
        }
    }

}