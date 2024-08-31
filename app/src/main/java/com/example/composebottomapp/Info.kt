package com.example.composebottomapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composebottomapp.ui.theme.ComposeBottomAppTheme

class Info : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBottomAppTheme{
                Surface(
              modifier = Modifier
                  .fillMaxSize()
                  .windowInsetsPadding(WindowInsets.systemBars),
              color = MaterialTheme.colorScheme.background
          ) {

              Scaffold(
                  topBar = {
                      TopAppBar(
                          title = {
                              Text(text = "info")
                          },
                          navigationIcon = {
                              IconButton(
                                  onClick = {
                                        Intent(applicationContext, MainActivity::class.java).also {
                                            startActivity(it)
                                        }
                                  }){
                                  Image(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                      contentDescription = null)
                              }
                          },
                          actions = {
                                    IconButton(onClick = {  }
                                    ) {
                                        Image(
                                            imageVector = Icons.Default.AccountCircle,
                                            contentDescription = null)
                                    }
                          },

                      )

                  }

              ) {
                  Spacer(modifier = Modifier.padding(12.dp))
                  Column(
                      modifier = Modifier
                          .fillMaxSize()
                          .padding(58.dp),

                      horizontalAlignment = Alignment.Start,
                      verticalArrangement = Arrangement.Top
                  ) {
                          Text(text = "weight :")
                          Spacer(modifier = Modifier.padding(8.dp))
                          Text(text = "gender :")
                      }

                  }
              }
          }
        }
    }
}