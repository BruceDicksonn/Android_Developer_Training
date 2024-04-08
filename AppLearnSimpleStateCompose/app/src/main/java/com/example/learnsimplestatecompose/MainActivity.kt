package com.example.learnsimplestatecompose

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.*
import com.example.learnsimplestatecompose.ui.theme.LearnSimpleStateComposeTheme
import com.example.learnsimplestatecompose.viewmodel.FormViewModel

lateinit var viewModel:FormViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)
            .create(FormViewModel::class.java)

        setContent {
            LearnSimpleStateComposeTheme {
                // A surface container using the 'background' color from the theme
                myApp()
            }
        }
    }
}

@Composable
fun myApp(){
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(),color = MaterialTheme.colors.background) {
            UserForm()
        }
    }
}

@Composable
fun UserForm() {

//    in case of standard viewModel, when we not use MutableLiveData to set attributes
//    val email:String   by viewModel._email.observeAsState(initial = "")
//    val phone:String   by viewModel._phone.observeAsState(initial = "")


//    stores the data enuring its integrity in lifecycle changes case
    val name:String    by rememberSaveable { viewModel._name  }
    val email:String   by rememberSaveable { viewModel._email  }
    val phone:String   by rememberSaveable { viewModel._phone  }

    var display:String by rememberSaveable { mutableStateOf("") }


    Column( Modifier.padding(Dp(16f))) {

        OutlinedTextField(value = name, onValueChange = { viewModel.onNameChange(it)  }, label = { Text(text = "Name") } )
        OutlinedTextField(value = email,onValueChange = { viewModel.onEmailChange(it) }, label = { Text(text = "Email") } )
        OutlinedTextField(value = phone,onValueChange = { viewModel.onPhoneChange(it) }, label = { Text(text = "Phone") } )

        Button( onClick = {
            display = "{ name:$name, email:$email, phone:$phone }"
        }){ Text(text = "Save") }

        if(!display.isEmpty()) {
            Text(text = display)
        }

    }

}














