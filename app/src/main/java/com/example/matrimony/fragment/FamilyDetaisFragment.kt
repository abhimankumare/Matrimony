package com.example.matrimony.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.matrimony.R
import com.example.poultry_i.common.Utils

/**
 * A simple [Fragment] subclass.
 * Use the [FamilyDetaisFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FamilyDetaisFragment : Fragment() {
    var familyDetails: LinearLayout? = null
    var aboutFamily: TextView? = null
    var moderate_nuclear_family_upper_middle: TextView? = null
    var family_income: TextView? = null
    var tv_fam_income: TextView? = null
    var father_occupation: TextView? = null
    var mother_occupation: TextView? = null
    var brother_sister: TextView? = null
    var tv_bro_marr: TextView? = null
    var tv_sis_marr: TextView? = null
    var family_based_out_of: TextView? = null
    var nagpur_maharashtra: TextView? = null

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
        //return inflater.inflate(R.layout.fragment_family_detais, container, false);
        val view: View = inflater.inflate(R.layout.fragment_family_detais, container, false)
        aboutFamily = view.findViewById(R.id.tv_aboutfamily)
        moderate_nuclear_family_upper_middle = view.findViewById(R.id.tv_moderate_nuclear_family_upper_middle)
        moderate_nuclear_family_upper_middle!!.text = Utils.AboutFamily.toString()
        family_income = view.findViewById(R.id.tv_family_income)
        tv_fam_income = view.findViewById(R.id.tv_fam_income)
        tv_fam_income!!.text = Utils.famincomeDetails.toString()
        father_occupation = view.findViewById(R.id.tv_father_occupation)
        father_occupation!!.text = Utils.foccuDetails.toString()
        mother_occupation = view.findViewById(R.id.tv_mother_occupation)
        mother_occupation!!.text = Utils.moccuDetails.toString()
        brother_sister = view.findViewById(R.id.tv_brother_sister)
        tv_sis_marr = view.findViewById(R.id.tv_sis_marr)
        tv_sis_marr!!.text = Utils.noofsis.toString()+" Sister of which "+Utils.noofmarrsis.toString()+" Married"
        tv_bro_marr = view.findViewById(R.id.tv_bro_marr)
        tv_bro_marr!!.text = Utils.noofbro.toString()+" Brother of which "+Utils.noofmarrbro.toString()+" Married"
        tv_fam_income!!.text = Utils.famincomeDetails.toString()
        family_based_out_of = view.findViewById(R.id.tv_family_based_out_of)
        nagpur_maharashtra = view.findViewById(R.id.tv_nagpur_maharashtra)
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
         * @return A new instance of fragment FamilyDetaisFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): FamilyDetaisFragment {
            val fragment = FamilyDetaisFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}