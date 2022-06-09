package com.cuyer.restaurantio.domain.services

import android.content.Intent

interface AccountService {
    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun createAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
}