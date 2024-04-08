package com.example.learnsimplestatecompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FormViewModel : ViewModel() {

    val _name  = mutableStateOf("")
    val _email = mutableStateOf("")
    val _phone = mutableStateOf("")

    fun onNameChange  ( newName: String   ) { _name.value  = newName   }
    fun onEmailChange ( newEmail: String  ) { _email.value = newEmail }
    fun onPhoneChange ( newPhone: String  ) { _phone.value = newPhone }

}