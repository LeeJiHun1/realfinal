package com.example.final123

import android.os.Bundle
import android.provider.Settings.Global.putString
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            if(edit?.text.toString() == "" ){
                Toast.makeText(requireContext(), "주소를 입력해주세요", Toast.LENGTH_LONG).show()
            }else {
                findNavController().navigate(
                    R.id.action_searchFragment_to_resultFragment,
                    Bundle().apply {

                        if(radioButton_lnmAdres?.isChecked == true){
                            putString("SEARCHSE", "road")
                        }else putString("SEARCHSE","dong" )

                        putString("WHERE", edit?.text.toString())
                    }
                )
            }
        }
    }

}