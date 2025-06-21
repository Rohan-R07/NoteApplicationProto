package com.practice.roomproto.Navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.practice.roomproto.Routes.Routes
import com.practice.roomproto.Screens.AddingScreen
import com.practice.roomproto.Screens.MainScreen
import com.practice.roomproto.UserViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UserNavigation(navController: NavHostController, viewModel: UserViewModel,snackBar: androidx.compose.material3.SnackbarHostState) {

    AnimatedNavHost(startDestination = Routes.MainScreen.route, navController = navController) {

        composable(
            route = Routes.MainScreen.route,
            exitTransition = {
                slideOutHorizontally()
            },
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                )+ slideInHorizontally { it }
            }
        ) {
            MainScreen(viewModel, navController)
        }
        composable(
            route = Routes.AddingScreen.route,
            exitTransition = {fadeOut(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                )
            },
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            }
        ) {
            AddingScreen(viewModel, navController,snackBar)
        }


    }

}