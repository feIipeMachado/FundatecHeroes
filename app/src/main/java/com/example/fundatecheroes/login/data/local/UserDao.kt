package com.example.fundatecheroes.login.data.local

import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.Date

@Dao
interface UserDao {
    @Insert
    fun insertUser(userEntity: UserEntity)

    @Query("SELECT date FROM user_table")
    fun getUserDate(): Date?

    @Query("DELETE FROM user_table")
    fun clearCache()

    @Query("SELECT id FROM user_table")
    fun getUserId(): Int
}