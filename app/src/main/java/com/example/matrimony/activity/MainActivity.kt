package com.example.matrimony.activity

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.matrimony.R
import com.example.matrimony.fragment.ChatFragment
import com.example.matrimony.fragment.HomeFragment
import com.example.matrimony.fragment.InboxFragment
import com.example.matrimony.fragment.SearchFragment
import com.example.matrimony.model.LoginResponse
import com.example.matrimony.model.MasterContent
import com.example.matrimony.model.MasterResponse
import com.example.matrimony.repository.ApiInterface
import com.example.poultry_i.common.Utils
import com.example.poultry_i.common.Utils.BlurBuilder.blur
import com.example.poultry_i.storageHelpers.PreferenceHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import android.graphics.Bitmap
import java.io.File
import android.widget.Toast

import android.util.DisplayMetrics
import com.bumptech.glide.Glide
import com.example.matrimony.model.ProfileResponse
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get
import java.nio.file.Paths.get


class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var imageView: ImageView
    lateinit var img_profile: ImageView
    val REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private lateinit var bottomNavigationView: BottomNavigationView
    var myLocale: Locale? = null

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
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        assert(navView != null)
        navView.setNavigationItemSelectedListener(this@MainActivity)
        val headerView: View = navView.getHeaderView(0)
        checkAndRequestPermissions()
        imageView = headerView.findViewById(R.id.imageView)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.hamburgerdr)
        val blurredBitmap = blur(this@MainActivity, bitmap)
        imageView.setBackgroundDrawable(BitmapDrawable(resources, blurredBitmap))

        getProfileData()
        img_profile = headerView.findViewById(R.id.img_profile)
//        val imgFile = File(Utils.urifile)
//
//        if (imgFile.exists()) {
//            val myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
//            img_profile.setImageBitmap(myBitmap)
//        }
      //  Utils.currentLanguage = PreferenceHelper.getStringPreference(this@MainActivity, "currentLanguage").toString()
        addFragment(HomeFragment(), false, HomeFragment::class.java.simpleName)
        initClickListeners()

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
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_noification -> {
                Utils.showLanguage(
                    "Please Select Language",
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                setLocale("en");
                                dialog.dismiss()
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                setLocale("hi");
                                dialog.dismiss()
                            }
                            DialogInterface.BUTTON_NEUTRAL -> {
                                setLocale("mr");
                                dialog.dismiss()
                            }
                        }
                    }, this
                )
//                addFragment(
//                    NotificationFragment(),
//                    true,
//                    NotificationFragment::class.java.simpleName
//                )
                return true
            }

        }
        return actionBarDrawerToggle!!.onOptionsItemSelected(item)
    }

    private fun checkAndRequestPermissions(): Boolean {
        val camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val storage =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val loc =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        val loc2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (loc2 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (loc != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_view_profile -> {
//                addFragment(
//                    HomeFragment(),
//                    true,
//                    HomeFragment::class.java.simpleName
//                )
                val intent = Intent(this@MainActivity, ShowDetails::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.nav_hide -> {
                val intent = Intent(this@MainActivity, HideProfileActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.nav_success -> {
//                Utils.showLanguage(
//                    "Please Select Language",
//                    DialogInterface.OnClickListener { dialog, which ->
//                        when (which) {
//                            DialogInterface.BUTTON_POSITIVE -> {
//                                setLocale("en");
//                                dialog.dismiss()
//                            }
//                            DialogInterface.BUTTON_NEGATIVE -> {
//                                setLocale("hi");
//                                dialog.dismiss()
//                            }
//                        }
//                    }, this
//                )
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.nav_changepassword -> {
                val intent = Intent(this@MainActivity, ChangePasswordActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.nav_about -> {
                var intent = Intent(
                    this@MainActivity,
                    CommonWebViewActivity::class.java
                )
                intent.putExtra("toolbar_title", "About Us")
                intent.putExtra(
                    "url",
                    "https://thehappinessindia.com/terms.php"
                )
                startActivity(intent)
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }


            R.id.nav_terms -> {
                var intent = Intent(
                    this@MainActivity,
                    CommonWebViewActivity::class.java
                )
                intent.putExtra("toolbar_title", "Terms and Condition")
                intent.putExtra(
                    "url",
                    "https://thehappinessindia.com/terms.php"
                )
                startActivity(intent)
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.nav_help -> {
                var intent = Intent(
                    this@MainActivity,
                    CommonWebViewActivity::class.java
                )
                intent.putExtra("toolbar_title", "Help and Support")
                intent.putExtra(
                    "url",
                    "https://thehappinessindia.com/terms.php"
                )
                startActivity(intent)
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.nav_privacy -> {
                var intent = Intent(
                    this@MainActivity,
                    CommonWebViewActivity::class.java
                )
                intent.putExtra("toolbar_title", "Privacy Policy")
                intent.putExtra(
                    "url",
                    "https://thehappinessindia.com/terms.php"
                )
                startActivity(intent)
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.nav_faq -> {
                var intent = Intent(
                    this@MainActivity,
                    CommonWebViewActivity::class.java
                )
                intent.putExtra("toolbar_title", "FAQ")
                intent.putExtra(
                    "url",
                    "https://thehappinessindia.com/terms.php"
                )
                startActivity(intent)
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.nav_rate -> {
                val marketUri =
                    Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.katana&hl=en")
                val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                startActivity(marketIntent)
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }




            R.id.nav_logout -> {
                Utils.showDialog(
                    "Are you sure you want to logout?",
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                dialog.dismiss()
                              logoutapi()
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog.dismiss()
                            }
                        }
                    }, this
                )
                return true
            }
        }
        return actionBarDrawerToggle!!.onOptionsItemSelected(item)
    }

    fun setLocale(localeName: String) {

        if (localeName != Utils.currentLanguage) {
            myLocale = Locale(localeName)
            val res: Resources = resources
            val dm: DisplayMetrics = res.getDisplayMetrics()
            val conf: Configuration = res.getConfiguration()
            conf.locale = myLocale
            res.updateConfiguration(conf, dm)
            Utils.currentLanguage = localeName
            PreferenceHelper.setStringPreference(this@MainActivity,"currentLanguage",Utils.currentLanguage)
            val refresh = Intent(this, MainActivity::class.java)
            refresh.putExtra(Utils.currentLanguage, localeName)
            startActivity(refresh)
        } else {
            Toast.makeText(this@MainActivity, "Language already selected!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun logoutapi() {
        try {
            if (Utils.isConnectingToInternet(this@MainActivity)) {
                val retIn = ApiInterface.RetrofitInstance.getRetrofitInstance()
                    .create(ApiInterface::class.java)
                retIn.LogoutData().enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.code() == 200) {
                            //  progressBar.visibility=View.VISIBLE
                            val responseBody: LoginResponse? = response.body()
                            if (responseBody != null) {
                                Toast.makeText(this@MainActivity, ""+responseBody.message.toString(), Toast.LENGTH_SHORT).show()
                                PreferenceHelper.clearValueForKey(this@MainActivity, "token")
                                gotoLoginActivity()
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

    private fun gotoLoginActivity() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }


    private fun initClickListeners() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.action_matches -> {
                    bottomNavigationView.getMenu().getItem(0)
                        .setIcon(R.drawable.matches);
                    addFragment(
                        HomeFragment(),
                        true,
                        HomeFragment::class.java.simpleName
                    )
                }

                R.id.action_search -> {
                    bottomNavigationView.getMenu().getItem(1)
                        .setIcon(R.drawable.search);
                    addFragment(
                        SearchFragment(),
                        true,
                        SearchFragment::class.java.simpleName
                    )
                }


                R.id.action_inbox -> {
                    bottomNavigationView.getMenu().getItem(3)
                        .setIcon(R.drawable.inbox);
                    addFragment(
                        InboxFragment(),
                        true,
                        InboxFragment::class.java.simpleName
                    )
                }

                R.id.action_chat -> {
                    bottomNavigationView.getMenu().getItem(4)
                        .setIcon(R.drawable.chat);
                    addFragment(
                        ChatFragment(),
                        true,
                        ChatFragment::class.java.simpleName
                    )
                }

            }
            true
        }


    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
           // bottomNavigationView.selectedItemId = R.id.action_home
        } else if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        } else {
            Utils.showDialogExitApp(
                "Are you sure you want to close the app?",
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            dialog.dismiss()
                            finishAffinity()
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }, this
            )

        }
    }


    fun getProfileData() {
        try {
            if (Utils.isConnectingToInternet(this@MainActivity)) {
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
                                //pr_bar!!.visibility =View.GONE
                                Utils.urifile = responseBody.userDetails!!.image.toString()
                                Glide.with(this@MainActivity).load(Utils.urifile).into(img_profile);


                            }
                        }else{
                          //  pr_bar!!.visibility =View.GONE
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

}