package com.example.composebottomapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ThemeChanger : ViewModel(){

    var backgroundColor by mutableStateOf(Color.White)
        private set

    fun themeChanger(){
        backgroundColor = Color.Cyan
    }

}
