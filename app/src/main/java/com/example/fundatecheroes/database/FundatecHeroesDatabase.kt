package com.example.fundatecheroes.database

import androidx.room.*
import com.example.fundatecheroes.App
import com.example.fundatecheroes.database.converter.Converters
import com.example.fundatecheroes.login.data.local.UserDao
import com.example.fundatecheroes.login.data.local.UserEntity

@Database(entities = [UserEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class FundatecHeroesDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun getInstance(): FundatecHeroesDatabase {
            return Room.databaseBuilder(
                App.context,
                FundatecHeroesDatabase::class.java,
                "fh.database"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        }
    }
}