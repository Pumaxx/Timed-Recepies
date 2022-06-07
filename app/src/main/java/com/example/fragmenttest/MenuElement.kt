package com.example.fragmenttest

class MenuElement {
    var elementId: Int = 0
    private var menuRecipeTitle = "Recipe title"

    fun getMenuRecipeTitle(): String {
        return menuRecipeTitle
    }

    fun getId(): Int {
        return elementId
    }

    fun setMenuRecipeTitle(title: String) {
        menuRecipeTitle = title
    }
}