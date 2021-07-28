package com.example.matrimony.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import com.example.matrimony.R


private val SPLASH_DELAY: Long = 2000
private var mDelayHandler: Handler? = null

lateinit var splash: ImageView
lateinit var splash1: ImageView
lateinit var splash2: ImageView
lateinit var splash3: ImageView


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splash = findViewById(R.id.splash)
        splash1 = findViewById(R.id.splash1)
        splash2 = findViewById(R.id.splash2)
        splash3 = findViewById(R.id.splash3)

        mDelayHandler = Handler()
//Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
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