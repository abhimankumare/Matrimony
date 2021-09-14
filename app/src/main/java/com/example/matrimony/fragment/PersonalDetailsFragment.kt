package com.example.matrimony.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.matrimony.R
import com.example.poultry_i.common.Utils

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonalDetailsFragment : Fragment() {
    var about: TextView? = null
    var tv_userName: TextView? = null
    var textab: TextView? = null
    var tv_profile_manage: TextView? = null
    var basic_details: TextView? = null
    var birth_date: TextView? = null
    var born_on_12_01_1991: TextView? = null
    var lives_in: TextView? = null
    var tv_city_state: TextView? = null
    var religion_mother_tongue: TextView? = null
    var tv_caste_religion: TextView? = null
    var marital_status: TextView? = null
    var never_married: TextView? = null
    var pd_community: TextView? = null
    var tv_community: TextView? = null
    var diet_preference: TextView? = null
    var non_vegetarian: TextView? = null
    var contact_details: TextView? = null
    var contact_no: TextView? = null
    var pd_email_id: TextView? = null
    var gmail_com: TextView? = null
    var get_contact_information_by: TextView? = null
    var education_career: TextView? = null
    var qualification: TextView? = null
    var tv_education: TextView? = null
    var profession: TextView? = null
    var architect: TextView? = null
    var education_field: TextView? = null
    var design: TextView? = null
    var occupation: TextView? = null
    var own_business: TextView? = null
    var college_name: TextView? = null
    var Clg_Pass: TextView? = null
    var annual_income: TextView? = null
    var tv_income: TextView? = null
    var Kundli_Astro: TextView? = null
    var user_has_not_given_horoscope: TextView? = null
    var Ipin: ImageView? = null
    var Ical: ImageView? = null
    var Ilocation: ImageView? = null
    var Ireligion: ImageView? = null
    var Imarital: ImageView? = null
    var Icommunity: ImageView? = null
    var Idiet: ImageView? = null
    var Itag: ImageView? = null
    var Icall: ImageView? = null
    var Icontact_lock: ImageView? = null
    var Imail: ImageView? = null
    var Iemail_lock: ImageView? = null
    var Iqualification: ImageView? = null
    var Iprofession: ImageView? = null
    var Ieducation: ImageView? = null
    var Ioccupation: ImageView? = null
    var Icollege: ImageView? = null
    var Iclg_lock: ImageView? = null
    var Ianual_income: ImageView? = null

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_personal_details, container, false);
        val view = inflater.inflate(R.layout.fragment_personal_details, container, false)
        about = view.findViewById(R.id.tv_pdAbout)

        tv_userName = view.findViewById(R.id.tv_userName)
        tv_userName!!.text = Utils.UserName.toString()


        textab = view.findViewById(R.id.tv_text)
        textab!!.text = Utils.user_bio.toString()
        tv_profile_manage = view.findViewById(R.id.tv_profile_manage)
        if(Utils.Gender.equals("Male")){
            tv_profile_manage!!.text = "His Profile is managed by Self"
        }else{
            tv_profile_manage!!.text = "Her Profile is managed by Self"
        }

        basic_details = view.findViewById(R.id.tv_basic_details)
        tv_city_state = view.findViewById(R.id.tv_city_state)

        birth_date = view.findViewById(R.id.tv_birth_date)
        birth_date!!.text = "Born On "+Utils.BirthDate.toString()

        never_married = view.findViewById(R.id.tv_never_married)
        never_married!!.text = Utils.MarriedStatus.toString()


        tv_city_state!!.text = Utils.CityName.toString()+","+Utils.StateName.toString()


        religion_mother_tongue = view.findViewById(R.id.tv_religion_mother_tongue)

        tv_caste_religion = view.findViewById(R.id.tv_caste_religion)
        tv_caste_religion!!.text = Utils.religionName.toString()+","+Utils.mothertoungeName.toString()

        tv_community = view.findViewById(R.id.tv_community)
        tv_community!!.text = Utils.casteName.toString()

        marital_status = view.findViewById(R.id.tv_marital_status)
        marital_status!!.text = Utils.MarriedStatus.toString()

        tv_education = view.findViewById(R.id.tv_education)
        tv_education!!.text = Utils.educationName.toString()

        own_business = view.findViewById(R.id.tv_own_business)
        own_business!!.text = Utils.occupationName.toString()

        tv_income = view.findViewById(R.id.tv_income)
        tv_income!!.text = Utils.incomeDetails.toString()

        pd_community = view.findViewById(R.id.tv_pd_community)

        diet_preference = view.findViewById(R.id.tv_diet_preference)
        non_vegetarian = view.findViewById(R.id.tv_non_vegetarian)
        contact_details = view.findViewById(R.id.tv_contact_details)
        contact_no = view.findViewById(R.id.tv_contact_no)
        pd_email_id = view.findViewById(R.id.tv_pd_email_id)
        gmail_com = view.findViewById(R.id.tv_gmail_com)
        get_contact_information_by = view.findViewById(R.id.tv_get_contact_information_by)
        education_career = view.findViewById(R.id.tv_education_career)
        qualification = view.findViewById(R.id.tv_qualification)

        profession = view.findViewById(R.id.tv_profession)
        architect = view.findViewById(R.id.tv_architect)
        education_field = view.findViewById(R.id.tv_education_field)
        design = view.findViewById(R.id.tv_design)
        occupation = view.findViewById(R.id.tv_occupation)

        college_name = view.findViewById(R.id.tv_college_name)
        Clg_Pass = view.findViewById(R.id.tv_Clg_Pass)
        annual_income = view.findViewById(R.id.tv_annual_income)

        Kundli_Astro = view.findViewById(R.id.tv_Kundli_Astro)
        user_has_not_given_horoscope = view.findViewById(R.id.tv_user_has_not_given_horoscope)
        Ipin = view.findViewById(R.id.iv_pin)
        Ical = view.findViewById(R.id.iv_cal)
        Ilocation = view.findViewById(R.id.iv_location)
        Ireligion = view.findViewById(R.id.iv_religion)
        Icommunity = view.findViewById(R.id.iv_community)
        Icontact_lock = view.findViewById(R.id.iv_contact_lock)
        Itag = view.findViewById(R.id.iv_tag)
        Icall = view.findViewById(R.id.iv_call)
        Imail = view.findViewById(R.id.iv_mail)
        Iemail_lock = view.findViewById(R.id.iv_email_lock)
        Iqualification = view.findViewById(R.id.iv_qualification)
        Iprofession = view.findViewById(R.id.iv_profession)
        Ieducation = view.findViewById(R.id.iv_education)
        Ioccupation = view.findViewById(R.id.iv_occupation)
        Icollege = view.findViewById(R.id.iv_college)
        Iclg_lock = view.findViewById(R.id.iv_clg_lock)
        Ianual_income = view.findViewById(R.id.iv_anual_income)

        setData()
        return view
        /*
        */
    }

    private fun setData() {

    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PersonalDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): PersonalDetailsFragment {
            val fragment = PersonalDetailsFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}