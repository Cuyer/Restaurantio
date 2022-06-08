package com.cuyer.restaurantio.domain.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.cuyer.restaurantio.domain.services.AccountServiceImpl

class LoginViewModel(application: Application) : AndroidViewModel(application) {


    var emailAuth by mutableStateOf("")

    var passAuth by mutableStateOf("")

    var passIconChecked by mutableStateOf(true)



    fun authValidation(onLogInClick: () -> Unit) {
        if (emailAuth.isEmpty()) {
            Toast.makeText(getApplication(), "Podaj swój email", Toast.LENGTH_SHORT).show()
        } else if (passAuth.isEmpty()) {
            Toast.makeText(getApplication(), "Podaj swoje hasło", Toast.LENGTH_SHORT).show()
        } else {
            AccountServiceImpl().authenticate(emailAuth, passAuth) { error ->
                if (error != null) {
                    Log.d("TAG", "authValidation: Something went wrong")
                } else {
                    onLogInClick.invoke()
                }
            }
        }
    }
}