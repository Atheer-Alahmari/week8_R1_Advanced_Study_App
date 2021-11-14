package com.example.week8_r1_advancedstudyapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week8_r1_advancedstudyapp.*

class AndriodFragment : Fragment()    {
    lateinit var dbhr: MyDBHelper
lateinit var list_RV: RecyclerView
var listKotlin = ArrayList<Study>()
lateinit var sharedPreferences: SharedPreferences

lateinit var titleED: EditText
lateinit var descED: EditText
lateinit var addBtn: Button
override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    // Inflate the layout for this fragment
    var view = inflater.inflate(R.layout.fragment_andriod, container, false)
    sharedPreferences = requireActivity().getSharedPreferences("", Context.MODE_PRIVATE)

    list_RV = view.findViewById(R.id.rv_Android)// Recycler View
    listKotlin= arrayListOf()
    titleED = view.findViewById(R.id.titelA_ED)
    descED = view.findViewById(R.id.desA_ED)
    addBtn = view.findViewById(R.id.addA_btn)

    dbhr = MyDBHelper(requireContext())

    updateList()
    addBtn.setOnClickListener {

        var inputTitel = titleED.text.toString()
        var inputDesc = descED.text.toString()

        //----------------Save DB--------------
        // var status=
        if (inputTitel.isNotEmpty() && inputDesc.isNotEmpty()) {
            dbhr.save_date("Android", inputTitel, inputDesc)

            Toast.makeText(activity, " Added ", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Please Enter a Note ", Toast.LENGTH_SHORT).show()

        }
        //---------------------------------------------------

        updateList()
        list_RV.adapter?.notifyDataSetChanged()

        titleED.text.clear()
        titleED.clearFocus()
        descED.text.clear()
        descED.clearFocus()

    }


    return view
}

fun updateList() {
    listKotlin = dbhr.retrive("Android")

    list_RV.adapter = RV_Adapter_Android(this, listKotlin)
    list_RV.layoutManager = LinearLayoutManager(requireContext())
    list_RV.scrollToPosition(listKotlin.size - 1)

}

}