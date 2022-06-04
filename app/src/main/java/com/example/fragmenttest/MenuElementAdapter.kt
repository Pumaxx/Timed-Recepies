package com.example.fragmenttest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.menu_element_content.view.*



class MenuElementAdapter(val menuElementsList: MutableList<MenuElement>
) : RecyclerView.Adapter<MenuElementAdapter.MenuElementViewHolder>() {

    class MenuElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuElementAdapter.MenuElementViewHolder {
        return MenuElementAdapter.MenuElementViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.menu_element_content,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuElementAdapter.MenuElementViewHolder, position: Int) {

        val currentMelement = menuElementsList[position]

        holder.itemView.apply {
            tvRecipeTitle.text = currentMelement.getMenuRecipeTitle()

            btGoToRecipe.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_recipeFragment)
            }

            btMenuElementEdit.setOnClickListener {
                etSetMenuElementTitle.visibility = View.VISIBLE
                btSetMenuElement.visibility = View.VISIBLE
                tvRecipeTitle.visibility = View.INVISIBLE
                btMenuElementEdit.visibility = View.INVISIBLE
                btMenuElementDelete.visibility = View.INVISIBLE
            }

            btSetMenuElement.setOnClickListener {
                if(etSetMenuElementTitle.text.toString().isEmpty())
                    currentMelement.setMenuRecipeTitle("Recipe title")
                else
                    currentMelement.setMenuRecipeTitle(etSetMenuElementTitle.text.toString())

                tvRecipeTitle.text = currentMelement.getMenuRecipeTitle()

                etSetMenuElementTitle.visibility = View.INVISIBLE
                btSetMenuElement.visibility = View.INVISIBLE
                tvRecipeTitle.visibility = View.VISIBLE
                btMenuElementEdit.visibility = View.VISIBLE
                btMenuElementDelete.visibility = View.VISIBLE
            }

            btMenuElementDelete.setOnClickListener {
                menuElementsList.remove(currentMelement)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return menuElementsList.size
    }

    fun addMenuElement(newMenuElement: MenuElement) {
        menuElementsList.add(newMenuElement)
        notifyItemInserted(menuElementsList.size - 1)
    }
}