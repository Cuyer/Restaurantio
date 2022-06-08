package com.cuyer.restaurantio.data.local.repository

import com.cuyer.restaurantio.data.local.UserDao
import com.cuyer.restaurantio.data.local.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class RestaurantioRepository(val UserDao: UserDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun findEmail(): String {
        var email = ""
        coroutineScope.launch(Dispatchers.IO) {
            email = UserDao.loadEmail()
        }
        return email
    }

    fun findPassword(): String {
        var password = ""
        coroutineScope.launch(Dispatchers.IO) {
            password = UserDao.loadPassword()
        }
        return password
    }

    fun insertData(firstName: String, email: String, password: String) {
        coroutineScope.launch(Dispatchers.IO) {
            UserDao.insertAll(UserEntity(
                uid = 0,
                firstName = firstName.trim(),
                email = email.trim(),
                password = password.trim()))
        }
    }
}