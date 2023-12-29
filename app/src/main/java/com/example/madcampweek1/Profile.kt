package com.example.madcampweek1

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

    fun setStudentData(studentData : ArrayList<ProfileDTO>){
        this.studentData = studentData
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPofileBinding.inflate(inflater, container, false)
       // val activity = activity as MainActivity?


        //val json = activity!!.getAssets().open("studentInfo.json").reader().readText()
        //val data = JSONObject(json).getJSONObject("studentList")
       // var jsonArray = JSONObject(json).getJSONArray("studentList")

       // val dataSet: ArrayList<ProfileDTO> = ArrayList()
        /*for (i in 0 until jsonArray.length()) {
            val name = jsonArray.getJSONObject(i).getString("name")
            val phoneNumber = jsonArray.getJSONObject(i).getString("phone_number")
            val studentID = jsonArray.getJSONObject(i).getString("studentID")
            val major = jsonArray.getJSONObject(i).getString("major")
            dataSet.add(ProfileDTO(name = name,
                studentID = studentID.toInt(),
                phoneNumber=phoneNumber,
                major=  Major.ComputerScience))
        }
*/

        val dataSet: ArrayList<ProfileDTO> = arrayListOf(
            ProfileDTO(name = "jiseop",
                studentID = 2020052633,
                phoneNumber="010-2468-9083",
                major= Major.ComputerScience),
            ProfileDTO(name = "jiseop2",
                studentID = 200526323,
                phoneNumber="010-2468-9083",
                major= Major.ComputerScience),
            ProfileDTO(name = "jiseop3",
                studentID = 2020523223,
                phoneNumber="010-2468-9083",
                major= Major.ComputerScience),
            ProfileDTO(name = "jiseop4",
                studentID = 2025264433,
                phoneNumber="010-2468-9083",
                major= Major.ComputerScience),
            ProfileDTO(name = "jiseop",
                studentID = 2020052633,
                phoneNumber="010-2468-9083",
                major= Major.ComputerScience),
            ProfileDTO(name = "jiseop",
                studentID = 2020052633,
                phoneNumber="010-2468-9083",
                major= Major.ComputerScience),
            ProfileDTO(name = "jiseop",
                studentID = 2020052633,
                phoneNumber="010-2468-9083",
                major= Major.ComputerScience),
        )






        profileAdapter = ProfileAdapter(dataSet)
        binding.profileItems.layoutManager = LinearLayoutManager(activity)
        binding.profileItems.adapter = profileAdapter


        return binding.root
    }
}