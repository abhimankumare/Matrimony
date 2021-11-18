package com.example.matrimony.activity

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.matrimony.R
import com.example.poultry_i.common.Utils


lateinit var ll_personal_detailsed: LinearLayout
lateinit var ed_mobileed: EditText
lateinit var etPassworded: EditText
lateinit var ed_emailed: EditText
lateinit var saveButtoned: Button


class EditRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_register)

        ll_personal_detailsed = findViewById(R.id.ll_personal_detailsed)
        ed_mobileed = findViewById(R.id.ed_mobileed)
        etPassworded = findViewById(R.id.etPassworded)
        saveButtoned = findViewById(R.id.saveButtoned)
        ed_emailed = findViewById(R.id.ed_emailed)

        ed_mobileed.setText(Utils.contact_no)
        ed_emailed.setText(Utils.email_id)

        saveButtoned.setOnClickListener(View.OnClickListener {

            //goToNextScreen()
            //Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()

            try {
                if(!ed_email.text.isNullOrBlank() && !Patterns.EMAIL_ADDRESS.matcher(ed_email.text.toString()).matches()) {
                    Utils.toast(this@EditRegisterActivity,"Please Enter Valid Email Id")
                }else if(ed_mobileemail.text.isNullOrBlank()) {
                    Utils.toast(this@EditRegisterActivity,"Please Enter Mobile Number")
                }else if(etPassword.text.isNullOrBlank()) {
                    Utils.toast(this@EditRegisterActivity,"Please Enter Password")
                } else {
                 /*   signUpProfile(user_type.toString(), Utils.UserName,
                        Utils.Gender, Utils.BirthDate,
                        DayTextView.text.toString(),edit_text_DOB_Time.text.toString(),
                        edit_text_birth_place.text.toString(),BloodGroupTextView.text.toString(),
                        edit_text_family_diety.text.toString(),
                        height_id.toString(), customerCountryTextView.text.toString(),
                        state_id.toString(), city_id.toString(),
                        ed_mobileemail.text.toString(),ed_email.text.toString(),
                        info_about.text.toString(), etPassword.text.toString(),
                        etPassword.text.toString())*/
                }
            } catch (err: Exception) {
                err.printStackTrace()
            }
        })


    }
}