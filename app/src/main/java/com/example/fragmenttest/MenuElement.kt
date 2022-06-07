package com.example.fragmenttest

class MenuElement {
    private var menuRecipeTitle = "Recipe title"

    fun getMenuRecipeTitle(): String {
        return menuRecipeTitle
    }

    fun setMenuRecipeTitle(title: String) {
        menuRecipeTitle = title
    }
}