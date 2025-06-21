package com.practice.roomproto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.practice.roomproto.Navigation.UserNavigation
import com.practice.roomproto.Screens.AddingScreen
import com.practice.roomproto.Screens.MainScreen
import com.practice.roomproto.ui.theme.RoomProtoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vmss by viewModels<UserViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return UserViewModel(application) as T
                    }
                }
            }
        )
        enableEdgeToEdge()
        setContent {
            RoomProtoTheme {

                Box(
                    modifier = Modifier
                        .background(
                            if (isSystemInDarkTheme()) MaterialTheme.colorScheme.secondaryContainer
                            else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                        )
                ) {

                    val navController = rememberNavController()
//                    }
                    val snackBarState = remember {
                        SnackbarHostState()
                    }

                    UserNavigation(navController, vmss, snackBar =snackBarState )
                }
            }
        }
    }
}

