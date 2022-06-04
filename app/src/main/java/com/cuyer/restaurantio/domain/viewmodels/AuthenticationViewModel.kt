package com.cuyer.restaurantio.domain.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.AndroidViewModel

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {

    var nameAuth by mutableStateOf("")

    var emailAuth by mutableStateOf("")

    var passAuth by mutableStateOf("")

    var passIconChecked by mutableStateOf(true)






    fun authValidation() {
        if (nameAuth.isEmpty()) {
            Toast.makeText(getApplication(), "Podaj swoje imię", Toast.LENGTH_SHORT).show()
        } else if (emailAuth.isEmpty()) {
            Toast.makeText(getApplication(), "Podaj swój email", Toast.LENGTH_SHORT).show()
        } else if (passAuth.isEmpty()) {
            Toast.makeText(getApplication(), "Podaj swoje hasło", Toast.LENGTH_SHORT).show()
        }
    }
}