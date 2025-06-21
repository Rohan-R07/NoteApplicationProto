package com.practice.roomproto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practice.roomproto.RoomDatabase.User
import com.practice.roomproto.RoomDatabase.UserDatabase
import com.practice.roomproto.RoomDatabase.mApplication
import kotlinx.coroutines.launch

class UserViewModel(applcation: Application) : AndroidViewModel(applcation) {

    private val db = (applcation as mApplication).database.userDao()

    val allUserlist = db.getAllUserData()

    init {
        fun getUserData() {
            db.getAllUserData()
        }
    }

    fun upersrinUser(user: User) {
        viewModelScope.launch {
            db.upsertUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch {
            db.deleteUser(user)
        }
    }

    fun updateUser(oldone: User,newName: String,newEmail: String,newPhoneNo: String){
        viewModelScope.launch {
            val newOne = oldone.copy(name = newName, email =newEmail , phoneNo =newPhoneNo )
            db.upsertUser(newOne)
        }
    }
}
