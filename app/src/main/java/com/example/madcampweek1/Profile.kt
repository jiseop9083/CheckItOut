package com.example.madcampweek1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.madcampweek1.databinding.FragmentPofileBinding
import org.json.JSONObject


class Profile : Fragment() {


    private lateinit var binding: FragmentPofileBinding
    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var studentData : ArrayList<ProfileDTO>

    lateinit var mContext: Context
    lateinit var mActivity: MainActivity


    fun setStudentData(studentData : ArrayList<ProfileDTO>){
        this.studentData = studentData
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mContext = context
            mActivity = activity as MainActivity
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPofileBinding.inflate(inflater, container, false)
        val activity = activity as MainActivity?


        val json = activity!!.getAssets().open("studentInfo.json").reader().readText()
        var jsonArray = JSONObject(json).getJSONArray("studentList")

        val dataSet: ArrayList<ProfileDTO> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            val name = jsonArray.getJSONObject(i).getString("name")
            val phoneNumber = jsonArray.getJSONObject(i).getString("phoneNumber")
            val studentID = jsonArray.getJSONObject(i).getString("studentID")
            //val major: Major = Major.valueOf(jsonArray.getJSONObject(i).getString("major"))
            dataSet.add(ProfileDTO(name=name,
                studentID=studentID.toInt(),
                phoneNumber=phoneNumber))
        }
        profileAdapter = ProfileAdapter(mContext, dataSet)
        binding.profileItems.layoutManager = LinearLayoutManager(activity)
        binding.profileItems.adapter = profileAdapter

        return binding.root
    }
}