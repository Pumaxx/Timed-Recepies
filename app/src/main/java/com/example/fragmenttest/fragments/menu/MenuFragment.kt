package com.example.fragmenttest.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fragmenttest.R
import kotlinx.android.synthetic.main.fragment_menu.view.*

class menuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        view.btMoveTo.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_recipeFragment)
        }

        return view
    }
}