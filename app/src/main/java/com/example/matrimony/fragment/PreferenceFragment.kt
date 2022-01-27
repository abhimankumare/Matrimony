package com.example.matrimony.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.matrimony.R

/**
 * A simple [Fragment] subclass.
 * Use the [PreferenceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreferenceFragment : Fragment() {
    var abcd1252_looking_for: TextView? = null
    var matching_8_out_of_12_her_preference: TextView? = null
    var her_partner: TextView? = null
    var your_match: TextView? = null
    var _25_to_28_yrs: TextView? = null
    var height: TextView? = null
    var heightSize: TextView? = null
    var religion_community: TextView? = null
    var hindu_96_kuli_maratha: TextView? = null
    var cast: TextView? = null
    var maratha: TextView? = null
    var mother_tongue: TextView? = null
    var marathi: TextView? = null
    var annual_income: TextView? = null
    var inr_3l_5l: TextView? = null
    var education: TextView? = null
    var educationDetails: TextView? = null
    var country: TextView? = null
    var india: TextView? = null
    var state: TextView? = null
    var maharastra: TextView? = null
    var city: TextView? = null
    var mumbai_pune_thane_navi_mumbai: TextView? = null
    var challenged: TextView? = null
    var none: TextView? = null
    var diet: TextView? = null
    var veg_non_veg: TextView? = null
    var common_between_you_amp_her: TextView? = null
    var both_belong_to_pune: TextView? = null
    var girl_circle: ImageView? = null
    var design: ImageView? = null
    var boy_circle: ImageView? = null
    var tickAge: ImageView? = null
    var tickHeight: ImageView? = null
    var tickReligion_community: ImageView? = null
    var tickAnnual_income: ImageView? = null
    var tickEducation: ImageView? = null
    var tickCountry: ImageView? = null
    var tickState: ImageView? = null
    var tickCity: ImageView? = null
    var tickChallenged: ImageView? = null
    var tickDiet: ImageView? = null
    var dot_both_belong_to_pune: ImageView? = null

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
        // return inflater.inflate(R.layout.fragment_preference, container, false);
        val view: View = inflater.inflate(R.layout.fragment_preference, container, false)
        abcd1252_looking_for = view.findViewById(R.id.tv_abcd1252_looking_for)
        matching_8_out_of_12_her_preference =
            view.findViewById(R.id.tv_matching_8_out_of_12_her_preference)
        her_partner = view.findViewById(R.id.tv_her_partner)
        your_match = view.findViewById(R.id.tv_your_match)
        _25_to_28_yrs = view.findViewById(R.id.tv_25_to_28_yrs)
        height = view.findViewById(R.id.tv_height)
        heightSize = view.findViewById(R.id.tv_heightSize)
        religion_community = view.findViewById(R.id.tv_religion_community)
        hindu_96_kuli_maratha = view.findViewById(R.id.tv_hindu_96_kuli_maratha)
        cast = view.findViewById(R.id.tv_cast)
        maratha = view.findViewById(R.id.tv_maratha)
        mother_tongue = view.findViewById(R.id.tv_mother_tongue)
        marathi = view.findViewById(R.id.tv_marathi)
        annual_income = view.findViewById(R.id.tv_annual_income)
        inr_3l_5l = view.findViewById(R.id.tv_inr_3l_5l)
        education = view.findViewById(R.id.tv_education)
        educationDetails = view.findViewById(R.id.tv_educationDetails)
        country = view.findViewById(R.id.tv_country)
        india = view.findViewById(R.id.tv_india)
        state = view.findViewById(R.id.tv_state)
        maharastra = view.findViewById(R.id.tv_maharastra)
        city = view.findViewById(R.id.tv_city)
        mumbai_pune_thane_navi_mumbai = view.findViewById(R.id.tv_mumbai_pune_thane_navi_mumbai)
        challenged = view.findViewById(R.id.tv_challenged)
        none = view.findViewById(R.id.tv_none)
        diet = view.findViewById(R.id.tv_diet)
        veg_non_veg = view.findViewById(R.id.tv_veg_non_veg)
        common_between_you_amp_her = view.findViewById(R.id.tv_common_between_you_amp_her)
        both_belong_to_pune = view.findViewById(R.id.tv_both_belong_to_pune)
        girl_circle = view.findViewById(R.id.iv_girl_circle)
        design = view.findViewById(R.id.iv_design)
        boy_circle = view.findViewById(R.id.iv_boy_circle)
        tickAge = view.findViewById(R.id.iv_tickAge)
        tickHeight = view.findViewById(R.id.iv_tickHeight)
        tickReligion_community = view.findViewById(R.id.iv_tickReligion_community)
        tickAnnual_income = view.findViewById(R.id.iv_tickAnnual_income)
        tickEducation = view.findViewById(R.id.iv_tickEducation)
        tickCountry = view.findViewById(R.id.iv_tickCountry)
        tickState = view.findViewById(R.id.iv_tickState)
        tickCity = view.findViewById(R.id.iv_tickCity)
        tickChallenged = view.findViewById(R.id.iv_tickChallenged)
        tickDiet = view.findViewById(R.id.iv_tickDiet)
        dot_both_belong_to_pune = view.findViewById(R.id.iv_dot_both_belong_to_pune)
        return view
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
         * @return A new instance of fragment PreferenceFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): PreferenceFragment {
            val fragment = PreferenceFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}