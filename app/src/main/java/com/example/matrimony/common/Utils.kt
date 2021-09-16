package com.example.poultry_i.common

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.poultry_i.storageHelpers.PreferenceHelper
import com.example.matrimony.R
import com.google.android.material.snackbar.Snackbar
import java.io.IOException


object Utils {

    var user_bio: String?=null
    var foccuDetails: String?=null
    var profile_saved: String?=null

    var moccuDetails: String?=null
    var incomeDetails: String?=null
    var famincomeDetails: String?=null
    var AboutFamily: String?=null

    var noofbro: String?=null
    var noofmarrbro: String?=null
    var noofsis: String?=null
    var noofmarrsis: String?=null

    var educationName: String?=null
    var Gender: String?=null
    var UserName: String?=null
    var mothertoungeName: String?=null
    var occupationName: String?=null

    var casteName: String?=null
    const val PATTERN_YEAR = "yyyy"
    const val PATTERN_MONTH = "MMM"
    const val PATTERN_MONTH_FULL = "MMMM"
    const val PATTERN_DAY_OF_MONTH = "dd"
    const val PATTERN_DAY_OF_WEEK = "EEEE"
    const val PATTERN_TIME = "hh:mm a"
    const val PATTERN_TIME_24H = "HH:mm"
    const val PATTERN_SERVER_DATE = "yyyy-MM-dd"
    const val PATTERN_SERVER_DATE_TIME = "yyyy-MM-dd HH:mm:ss"
    const val PATTERN_START_WITH_MONTH = "MMM dd , yyyy"
    const val PATTERN_START_WITH_MONTH_NO_YEAR = "MMMM dd"
    const val PATTERN_START_WITH_DATE_NO_YEAR = "dd MMMM"
    const val PATTERN_START_WITH_MONTH_SHORT_NO_YEAR = "MMM dd"
    const val PATTERN_START_WITH_MONTH_WITH_TIME = "MMM dd, yyyy HH:mm:ss"
    const val PATTERN_START_WITH_MONTH_SMALL_NO_YEAR = "MMM dd"
    var token : String? = null
    var BirthDate : String? = null
    var MarriedStatus : String? = null
    var CityName : String? = null
    var StateName : String? = null
    var religionName : String?= null
    @SuppressLint("MissingPermission")
    fun isConnectingToInternet(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }
    fun showDialogBasicData(
        message: String,
        okListener: DialogInterface.OnClickListener,
        context: Context
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setTitle("Warning")
        builder.setCancelable(false)
        builder.setPositiveButton("Yes", okListener)
        builder.setNegativeButton("No", okListener)
        builder.create()
        builder.show()
    }

    fun showDialog(
        message: String,
        okListener: DialogInterface.OnClickListener,
        context: Context
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setTitle("Warning")
        builder.setIcon(R.drawable.ic_baseline_exit_to_app_24)
        builder.setPositiveButton("Yes", okListener)
        builder.setNegativeButton("No", okListener)
        builder.create()
        builder.show()
    }

    fun showDialogExitApp(
        message: String,
        okListener: DialogInterface.OnClickListener,
        context: Context
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setTitle("Exit")
        builder.setPositiveButton("Yes", okListener)
        builder.setNegativeButton("No", okListener)
        builder.create()
        builder.show()
    }



    fun showDialog1(
        message: String,
        okListener: DialogInterface.OnClickListener,
        context: Context
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.setPositiveButton("OK", okListener)
        builder.create()
        builder.show()
    }

    fun toast(context:Context,message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showIndefiniteSnackBar(view: View, msg: String) {
        try {
            val snackbar = Snackbar.make(
                view,
                msg,
                10000
            )
            val textView =
                snackbar.view.findViewById(R.id.snackbar_text) as TextView
            textView.isSingleLine = false
            snackbar.setAction("OK") {
                snackbar.dismiss() }
            snackbar.show()
        } catch (err: Exception) {
            err.printStackTrace()
        }
    }


    fun clearPrefrences(context: Context) {
        PreferenceHelper.clearValueForKey(context, "token")
    }







    object BlurBuilder {
        private const val BITMAP_SCALE = 0.4f
        private const val BLUR_RADIUS = 25f
        fun blur(context: Context?, image: Bitmap): Bitmap {
            val width = Math.round(image.width * BITMAP_SCALE)
            val height = Math.round(image.height * BITMAP_SCALE)
            val inputBitmap = Bitmap.createScaledBitmap(image, width, height, false)
            val outputBitmap = Bitmap.createBitmap(inputBitmap)
            val rs = RenderScript.create(context)
            val theIntrinsic = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)) } else {
                TODO("VERSION.SDK_INT < JELLY_BEAN_MR1")
            }
            val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
            val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
          //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                theIntrinsic.setRadius(BLUR_RADIUS)
           // }
          //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                theIntrinsic.setInput(tmpIn)
          //  }
          //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                theIntrinsic.forEach(tmpOut)
          //  }
            tmpOut.copyTo(outputBitmap)
            return outputBitmap
        }
    }



}