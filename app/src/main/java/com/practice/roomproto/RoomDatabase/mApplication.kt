package com.practice.roomproto.RoomDatabase

import android.app.Application
import androidx.room.Room


class mApplication : Application() {

    lateinit var database: UserDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "user_database"
        ).addMigrations(migration_1_2, migration_2_3)
            .build()
    }
}