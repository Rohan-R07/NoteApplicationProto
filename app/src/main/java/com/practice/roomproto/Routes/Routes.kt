package com.practice.roomproto.Routes

const val mainScreen = "main_screen"
const val detailedScreen = "adding_screen"
const val id = "title"

sealed class Routes(
    val route: String = mainScreen
){
    object MainScreen: Routes(mainScreen)
//    object DetailScreen: Routes(detailedScreen+"/{id}"){
//        fun addData(id:Int) = "details/$id"
//    }

    object AddingScreen : Routes(detailedScreen)
}