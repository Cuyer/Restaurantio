package com.cuyer.restaurantio.domain.viewmodels

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.cuyer.restaurantio.data.local.RestaurantioDatabase
import com.cuyer.restaurantio.data.local.repository.RestaurantioRepository
import com.cuyer.restaurantio.domain.services.AccountServiceImpl
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException


class RegisterViewModel(application: Application) : AndroidViewModel(application) {


    var nameAuth by mutableStateOf("")

    var emailAuth by mutableStateOf("")

    var passAuth by mutableStateOf("")

    var passIconChecked by mutableStateOf(true)


        fun authValidation(onRegisterClick: () -> Unit) {
            if (nameAuth.isEmpty()) {
                Toast.makeText(getApplication(), "Podaj swoje imię", Toast.LENGTH_SHORT).show()
            } else if (emailAuth.isEmpty()) {
                Toast.makeText(getApplication(), "Podaj swój email", Toast.LENGTH_SHORT).show()
            } else if (passAuth.isEmpty()) {
                Toast.makeText(getApplication(), "Podaj swoje hasło", Toast.LENGTH_SHORT).show()
            } else {
                AccountServiceImpl().createAccount(emailAuth, passAuth) { error ->
                    if (error != null) {
                        Log.d("TAG", "authValidation: Something went wrong")
                    } else {
                        onRegisterClick.invoke()
                    }
                }
                val repository: RestaurantioRepository
                val db = RestaurantioDatabase.getInstance(getApplication())
                val dao = db.userDao()
                repository = RestaurantioRepository(dao)

                repository.insertData(
                    firstName = nameAuth,
                    email = emailAuth,
                    password = passAuth
                )
            }
        }
    }


