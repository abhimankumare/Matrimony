package com.example.matrimony.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.matrimony.R
import com.example.poultry_i.common.Utils
import com.example.poultry_i.storageHelpers.PreferenceHelper


private val SPLASH_DELAY: Long = 2000
private var mDelayHandler: Handler? = null

lateinit var splash: ImageView
lateinit var splash1: ImageView
lateinit var splash2: ImageView
lateinit var splash3: ImageView
lateinit var rl_root_view: RelativeLayout


var tokenonstart:String? = null
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splash = findViewById(R.id.splash)
        splash1 = findViewById(R.id.splash1)
        splash2 = findViewById(R.id.splash2)
        splash3 = findViewById(R.id.splash3)
        rl_root_view = findViewById(R.id.rl_root_view)


        tokenonstart = PreferenceHelper.getStringPreference(this@SplashActivity, "token").toString()
        Utils.token = tokenonstart
        startapp()
    }

    private fun startapp() {
        if (Utils.isConnectingToInternet(this@SplashActivity)) {
            if(tokenonstart.equals("null")){
                try {
                    mDelayHandler = Handler()
                    //Navigate with delay
                    mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
                } catch (err: Exception) {
                    err.printStackTrace()
                }
            }else{
                try {
                    mDelayHandler = Handler()
                    //Navigate with delay
                    mDelayHandler!!.postDelayed(mRunnableNow, SPLASH_DELAY)


                } catch (err: Exception) {
                    err.printStackTrace()
                }

            }
        }else{
            Utils.showIndefiniteSnackBar(
                rl_root_view,
                "You're offline, Please check your network connection."
            )
        }
    }


    internal val mRunnableNow: Runnable = Runnable {

        splash.visibility = View.GONE

        val intent = Intent(this@SplashActivity, MainActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()

    }

    internal val mRunnable: Runnable = Runnable {

        splash.visibility = View.GONE
        splash1.visibility = View.VISIBLE

        mDelayHandler!!.postDelayed(mRunnable1, SPLASH_DELAY)

    }


    internal val mRunnable1: Runnable = Runnable {

        splash1.visibility = View.GONE
        splash2.visibility = View.VISIBLE

        mDelayHandler!!.postDelayed(mRunnable2, SPLASH_DELAY)

    }

    internal val mRunnable2: Runnable = Runnable {

        splash2.visibility = View.GONE
        splash3.visibility = View.VISIBLE


        navigatetoactivity()

    }

    private fun navigatetoactivity() {

        try {
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        } catch (err: Exception) {
            err.printStackTrace()
        }
    }
}