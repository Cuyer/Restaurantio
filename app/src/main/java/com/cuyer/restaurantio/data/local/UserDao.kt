package com.cuyer.restaurantio.data.local

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    fun getAll(): List<UserEntity>

    @Query("SELECT email FROM UserEntity")
    fun loadEmail(): String

    @Query("SELECT password FROM UserEntity")
    fun loadPassword(): String

    @Insert
    fun insertAll(vararg userEntity: UserEntity)

    @Update
    fun updateUsers(vararg userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

}