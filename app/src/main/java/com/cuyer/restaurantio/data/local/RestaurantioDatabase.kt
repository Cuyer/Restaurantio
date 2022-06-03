package com.cuyer.restaurantio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 1)
abstract class RestaurantioDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}