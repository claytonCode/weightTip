package com.example.composebottomapp
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composebottomapp.ui.theme.ComposeBottomAppTheme
import com.example.composebottomapp.viewmodel.ThemeChanger

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBottomAppTheme {
                val viewModel = viewModel<ThemeChanger>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = viewModel.backgroundColor
                ){
                    var selectedItemState by remember {
                        mutableStateOf("Gender")
                    }
                    var newWeightState by remember {
                        mutableStateOf("")
                    }
                    var sheetState = rememberModalBottomSheetState()
                    var isSheetOpen by remember {
                        mutableStateOf(false)
                    }
                    if(isSheetOpen){
                        ModalBottomSheet(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            sheetState = sheetState ,
                            onDismissRequest = {
                                isSheetOpen = false
                            })
                        {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            )
                            {
                                Text(text = "Weight Notes")
                            }

                        }
                    }
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "weight notes")
                                },
                                navigationIcon = {
                                  IconButton(onClick = {  }
                                  ) {
                                      Icon(
                                          imageVector = Icons.Default.Menu,
                                          contentDescription = "menu"
                                      )
                                  }
                                },
                                actions = {
                                    IconButton(onClick = {
                                          isSheetOpen = true
                                    }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = ""
                                        )
                                    }
                                }
                            )
                        },

                        bottomBar = {
                            BottomAppBar(
                                actions = {
                                    IconButton(
                                        onClick = {
                                            val intent =  Intent(Intent.ACTION_SEND).apply {
                                                type = "text/plain"
                                                putExtra(Intent.EXTRA_TEXT, "weight $newWeightState KG")
                                            }
                                            if(intent.resolveActivity(packageManager) != null){
                                                startActivity(intent)
                                            }
                                        }) {
                                        Icon(
                                            imageVector = Icons.Default.Share,
                                            contentDescription = "share"
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            viewModel.themeChanger()
                                        }) {
                                        Icon(
                                            imageVector = Icons.Default.Favorite,
                                            contentDescription = "like"
                                        )
                                    }
                                    IconButton(
                                        onClick = {
                                            Intent(applicationContext, Info::class.java).also {
                                                startActivity(it)
                                            }

                                        }) {
                                        Icon(
                                            imageVector =  Icons.Filled.Face,
                                            contentDescription = "MAIL"
                                        )
                                    }

                                },

                                floatingActionButton = {
                                    FloatingActionButton(onClick = {
                                            newWeightState = ""
                                            selectedItemState = "Gender"

                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Refresh,
                                            contentDescription = "ADD"
                                        )
                                    }
                                }

                            )

                        },

                    ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                                    .windowInsetsPadding(WindowInsets.systemBars),

                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            ) {
                                var textState by remember {
                                    mutableStateOf("")

                                }
                                Spacer(modifier = Modifier.padding(30.dp))

                                OutlinedTextField(
                                    modifier = Modifier
                                        .width(280.dp)
                                        .height(65.dp)
                                        .padding(0.dp),

                                    value = textState ,
                                    onValueChange = { currentText ->
                                    textState = currentText

                                    },
                                    textStyle = LocalTextStyle.current.copy(
                                        textAlign = TextAlign.Right
                                    ),
                                    label = {
                                        Text(text = "weight")

                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Face,
                                            contentDescription = "face"
                                        )

                                    },
                                    prefix = {
                                        Text(text = "KG")
                                    },

                                )
                                Spacer(modifier = Modifier.padding(8.dp))

                                var expend by remember {
                                    mutableStateOf(false)
                                }

                                val gender = listOf("Male", "Female")

                                ExposedDropdownMenuBox(
                                    expanded = expend ,
                                    onExpandedChange = { expend = !expend}
                                ) {
                                    TextField(
                                        value = selectedItemState ,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expend)
                                        },
                                        textStyle = LocalTextStyle.current.copy(
                                            textAlign = TextAlign.Center
                                        ),

                                        modifier = Modifier
                                            .width(280.dp)
                                            .height(56.dp)
                                            .menuAnchor()
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expend,
                                        onDismissRequest = { expend = false}
                                    ) {
                                        gender.forEach{ item ->
                                                DropdownMenuItem(
                                                    text = { Text(item) },
                                                    onClick = {
                                                        selectedItemState = item
                                                        expend = false
                                                    },
                                                    modifier = Modifier
                                                        .width(280.dp)
                                                        .height(56.dp)
                                                )
                                        }

                                    }

                                }

                                Spacer(modifier = Modifier.padding(12.dp))

                                Button(onClick = {
                                        newWeightState = textState
                                        textState = ""
                                },
                                    modifier = Modifier
                                        .width(280.dp)
                                        .height(56.dp),
                                        shape = RectangleShape

                                ) {
                                    Text(text = "EVALUATE")
                                }
                                Spacer(modifier = Modifier.padding(1.dp))
                                TextField(
                                    value = newWeightState ,
                                    onValueChange = { newText ->
                                        newWeightState = newText
                                    },
                                    textStyle = LocalTextStyle.current.copy(
                                        textAlign = TextAlign.Center
                                    ),
                                    label = {
                                        Text(text = "")

                                    }, readOnly = true
                                )

                            }


                        }

                    }

                }

            }
        }
    }

