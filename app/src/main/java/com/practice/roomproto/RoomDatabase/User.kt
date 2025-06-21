package com.practice.roomproto.RoomDatabase

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String, // done
    val email:String, // Done
    val phoneNo: String,

    // version 2
    val title: String, // done
    val age: Int = 1, // done
    val currentDate: Long = System.currentTimeMillis(),

    // version 3
    //  Added fields
    val color: Long = 0xFFFFFFFF, // default white color
    val isFavorite: Boolean = false
)