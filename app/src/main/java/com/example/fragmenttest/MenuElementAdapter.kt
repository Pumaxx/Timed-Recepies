package com.example.fragmenttest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmenttest.preset.Preset
import kotlinx.android.synthetic.main.menu_element_content.view.*


class MenuElementAdapter(
    private val menuElementsList: MutableList<MenuElement>
) : RecyclerView.Adapter<MenuElementAdapter.MenuElementViewHolder>() {

    class MenuElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuElementViewHolder {
        return MenuElementViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.menu_element_content,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuElementViewHolder, position: Int) {

        val currentElement = menuElementsList[position]

        holder.itemView.apply {
            tvRecipeTitle.text = currentElement.getMenuRecipeTitle()

            btGoToRecipe.setOnClickListener {
                Preset.currentId = currentElement.getId()
                Log.d("Preset.currentId", Preset.currentId.toString())
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
                if (etSetMenuElementTitle.text.toString().isEmpty())
                    currentElement.setMenuRecipeTitle("Recipe title")
                else
                    currentElement.setMenuRecipeTitle(etSetMenuElementTitle.text.toString())

                tvRecipeTitle.text = currentElement.getMenuRecipeTitle()

                etSetMenuElementTitle.visibility = View.INVISIBLE
                btSetMenuElement.visibility = View.INVISIBLE
                tvRecipeTitle.visibility = View.VISIBLE
                btMenuElementEdit.visibility = View.VISIBLE
                btMenuElementDelete.visibility = View.VISIBLE
            }

            btMenuElementDelete.setOnClickListener {
                menuElementsList.remove(currentElement)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return menuElementsList.size
    }

    fun addMenuElement(newMenuElement: MenuElement) {
        newMenuElement.elementId = itemCount
        menuElementsList.add(newMenuElement)
        notifyItemInserted(menuElementsList.size - 1)
    }
}
