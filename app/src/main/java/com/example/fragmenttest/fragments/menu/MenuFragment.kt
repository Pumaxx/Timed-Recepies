package com.example.fragmenttest.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmenttest.MenuElement
import com.example.fragmenttest.MenuElementAdapter
import com.example.fragmenttest.R
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*

class menuFragment : Fragment() {

    private lateinit var menuElementAdapter: MenuElementAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        menuElementAdapter = MenuElementAdapter(mutableListOf())
        view.rvTMenuElementsContainer.adapter = menuElementAdapter
        view.rvTMenuElementsContainer.layoutManager = LinearLayoutManager(view.context)

        view.btAddNewRecipe.setOnClickListener {
            //findNavController().navigate(R.id.action_menuFragment_to_recipeFragment)
            val mElement = MenuElement()
            menuElementAdapter.addMenuElement(mElement)
        }

        return view
    }
}