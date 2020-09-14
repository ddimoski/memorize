package com.finki.mpip.memorize.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.finki.mpip.memorize.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE id LIKE :id")
    fun getById(id: String): LiveData<User>

    @Insert
    fun addUser(newUser: User): Long

    @Update
    fun updateUser(user: User): Int

    @Delete
    fun deleteUser(user: User): Int
}