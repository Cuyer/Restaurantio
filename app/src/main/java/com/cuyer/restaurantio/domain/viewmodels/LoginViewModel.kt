package com.cuyer.restaurantio.domain.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.cuyer.restaurantio.data.local.RestaurantioDatabase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var emailAuth by mutableStateOf("")

    var passAuth by mutableStateOf("")

    var passIconChecked by mutableStateOf(true)

    val db = Room.databaseBuilder(getApplication(),
        RestaurantioDatabase::class.java,
        "Restaurantio Database").build()
    val userDao = db.userDao()


    fun authValidation() {
         if (emailAuth.isEmpty()) {
            Toast.makeText(getApplication(), "Podaj swój email", Toast.LENGTH_SHORT).show()
        } else if (passAuth.isEmpty()) {
            Toast.makeText(getApplication(), "Podaj swoje hasło", Toast.LENGTH_SHORT).show()
        } else {
             authenticate()
         }
    }

    fun authenticate() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = userDao.loadEmail()
            val password = userDao.loadPassword()
            Firebase.auth.signInWithEmailAndPassword(email, password)
        }
    }
}