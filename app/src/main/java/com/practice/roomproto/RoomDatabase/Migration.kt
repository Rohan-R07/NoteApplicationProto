package com.practice.roomproto.RoomDatabase


import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val migration_1_2 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user ADD COLUMN title TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE user ADD COLUMN age INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE user ADD COLUMN currentDate INTEGER NOT NULL DEFAULT 0")
    }
}

val migration_2_3 = object : Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user ADD COLUMN color INTEGER NOT NULL DEFAULT 4294967295")
        database.execSQL("ALTER TABLE user ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")

    }
}


