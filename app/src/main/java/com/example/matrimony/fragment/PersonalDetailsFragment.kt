package com.example.matrimony.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.matrimony.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalDetailsFragment extends Fragment {
    TextView about,abcd1252,text,her_profile_is_managed_by_self,
            basic_details,birth_date,born_on_12_01_1991,lives_in,
            pune_maharashtra,religion_mother_tongue,hindu_maratha,
            marital_status,never_married,pd_community,_96_kuli_maratha,
            diet_preference,non_vegetarian,contact_details,contact_no,
            pd_email_id,gmail_com,get_contact_information_by,
            education_career,qualification,b_e_b_tect,profession,
            architect,education_field,design,occupation,own_business,
            college_name,Clg_Pass,annual_income,earn_inr_2l_to_4l,
            Kundli_Astro,user_has_not_given_horoscope;

    ImageView Ipin,Ical,Ilocation,Ireligion,Imarital,Icommunity,Idiet,
            Itag,Icall,Icontact_lock,Imail,Iemail_lock,Iqualification,
            Iprofession,Ieducation,Ioccupation,Icollege,Iclg_lock,
            Ianual_income;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonalDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalDetailsFragment newInstance(String param1, String param2) {
        PersonalDetailsFragment fragment = new PersonalDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_personal_details, container, false);
        View view =  inflater.inflate(R.layout.fragment_personal_details, container, false);
        about = view.findViewById(R.id.tv_pdAbout);
        abcd1252 = view.findViewById(R.id.tv_abcd1252);
        text = view.findViewById(R.id.tv_text);
        her_profile_is_managed_by_self = view.findViewById(R.id.tv_her_profile_is_managed_by_self);
        basic_details = view.findViewById(R.id.tv_basic_details);
        birth_date = view.findViewById(R.id.tv_birth_date);
        born_on_12_01_1991 = view.findViewById(R.id.tv_born_on_12_01_1991);
        religion_mother_tongue = view.findViewById(R.id.tv_religion_mother_tongue);
        pune_maharashtra = view.findViewById(R.id.tv_pune_maharashtra);
        hindu_maratha = view.findViewById(R.id.tv_hindu_maratha);
        marital_status = view.findViewById(R.id.tv_marital_status);
        never_married = view.findViewById(R.id.tv_never_married);
        pd_community = view.findViewById(R.id.tv_pd_community);
        _96_kuli_maratha = view.findViewById(R.id.tv_96_kuli_maratha);
        diet_preference = view.findViewById(R.id.tv_diet_preference);
        non_vegetarian =view.findViewById(R.id.tv_non_vegetarian);
        contact_details = view.findViewById(R.id.tv_contact_details);
        contact_no = view.findViewById(R.id.tv_contact_no);
        pd_email_id = view.findViewById(R.id.tv_pd_email_id);
        gmail_com = view.findViewById(R.id.tv_gmail_com);
        get_contact_information_by = view.findViewById(R.id.tv_get_contact_information_by);
        education_career = view.findViewById(R.id.tv_education_career);
        qualification = view.findViewById(R.id.tv_qualification);
        b_e_b_tect = view.findViewById(R.id.tv_b_e_b_tect);
        profession = view.findViewById(R.id.tv_profession);
        architect = view.findViewById(R.id.tv_architect);
        education_field = view.findViewById(R.id.tv_education_field);
        design = view.findViewById(R.id.tv_design);
        occupation = view.findViewById(R.id.tv_occupation);
        own_business = view.findViewById(R.id.tv_own_business);
        college_name = view.findViewById(R.id.tv_college_name);
        Clg_Pass = view.findViewById(R.id.tv_Clg_Pass);
        annual_income = view.findViewById(R.id.tv_annual_income);
        earn_inr_2l_to_4l = view.findViewById(R.id.tv_earn_inr_2l_to_4l);
        Kundli_Astro = view.findViewById(R.id.tv_Kundli_Astro);
        user_has_not_given_horoscope = view.findViewById(R.id.tv_user_has_not_given_horoscope);
        Ipin = view.findViewById(R.id.iv_pin);
        Ical = view.findViewById(R.id.iv_cal);
        Ilocation = view.findViewById(R.id.iv_location);
        Ireligion = view.findViewById(R.id.iv_religion);
        Icommunity = view.findViewById(R.id.iv_community);
        Icontact_lock = view.findViewById(R.id.iv_contact_lock);
        Itag = view.findViewById(R.id.iv_tag);
        Icall = view.findViewById(R.id.iv_call);
        Imail = view.findViewById(R.id.iv_mail);
        Iemail_lock = view.findViewById(R.id.iv_email_lock);
        Iqualification = view.findViewById(R.id.iv_qualification);
        Iprofession = view.findViewById(R.id.iv_profession);
        Ieducation = view.findViewById(R.id.iv_education);
        Ioccupation = view.findViewById(R.id.iv_occupation);
        Icollege = view.findViewById(R.id.iv_college);
        Iclg_lock = view.findViewById(R.id.iv_clg_lock);
        Ianual_income = view.findViewById(R.id.iv_anual_income);
        return view;
       /*
        */
    }

}