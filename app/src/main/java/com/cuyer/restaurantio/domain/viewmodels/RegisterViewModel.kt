package com.cuyer.restaurantio.domain.viewmodels

import android.app.Application
import android.util.Log
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
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.cuyer.restaurantio.data.local.RestaurantioDatabase
import com.cuyer.restaurantio.data.local.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    var nameAuth by mutableStateOf("")

    var emailAuth by mutableStateOf("")

    var passAuth by mutableStateOf("")

    var passIconChecked by mutableStateOf(true)



    val db = Room.databaseBuilder(getApplication(),
        RestaurantioDatabase::class.java,
        "Restaurantio Database").build()
    val userDao = db.userDao()




    fun authValidation() {
        if (nameAuth.isEmpty()) {
            Toast.makeText(getApplication(), "Podaj swoje imię", Toast.LENGTH_SHORT).show()
        } else if (emailAuth.isEmpty()) {
            Toast.makeText(getApplication(), "Podaj swój email", Toast.LENGTH_SHORT).show()
        } else if (passAuth.isEmpty()) {
            Toast.makeText(getApplication(), "Podaj swoje hasło", Toast.LENGTH_SHORT).show()
        } else {
            authDataToDatabase()
        }
    }

    fun authDataToDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertAll(UserEntity(uid = 0,
                firstName = nameAuth.trim(),
                email = emailAuth.trim(),
                password = passAuth.trim()))

            getDataFromDatabaseAndCreateAccount()

        }
    }

    fun getDataFromDatabaseAndCreateAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = userDao.loadEmail()
            val password = userDao.loadPassword()
            Firebase.auth.createUserWithEmailAndPassword(email, password)
            Firebase.auth.signInWithEmailAndPassword(email, password)
            Log.d("DatabaseResponse", "Email: $email Password: $password ")
        }
    }
}