package com.example.messagechat.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.messagechat.data.local.dao.UserDAO
import com.example.messagechat.data.local.models.User



@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDAO: UserDAO

    companion object {
        private lateinit var instance: AppDatabase
        private val DATABASE_NAME = "ChatDB"
        fun getDatabase(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (!::instance.isInitialized) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
                }
            }
            return instance
        }
    }
}

/* This is another way to call only getDatabase() from activity/fragment without call AppDatabase.getDatabase()
fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::instance.isInitialized) {
            instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
    return instance
} */