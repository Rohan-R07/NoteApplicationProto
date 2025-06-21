package com.practice.roomproto.Screens

import android.content.pm.ShortcutInfo
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.roomproto.Routes.Routes
import com.practice.roomproto.UserViewModel
import com.practice.roomproto.Utils.MCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MainScreen(
    viewModel: UserViewModel,
    navController: androidx.navigation.NavHostController,
) {
    val userList = viewModel.allUserlist.collectAsState(initial = emptyList())

    val lazyState = rememberLazyListState()
    val scroollStaet = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val contex = LocalContext.current

//                val ShortCut = ShortcutInfo.Builder(contex, "Dynamic")
//                    .setShortLabel("Add Screen")
//                    .

    Scaffold(

//        contentColor = MaterialTheme.colorScheme.onPrimary
        topBar = {

            CenterAlignedTopAppBar(
                scrollBehavior = scroollStaet,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.secondaryContainer.copy(
                        alpha = 0.1f
                    ) else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = {
                    Text(
                        text = "Room Proto",
                        modifier = Modifier,
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            )
        },
    ) { padding ->

        LazyColumn(
            state = lazyState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    if (isSystemInDarkTheme()) MaterialTheme.colorScheme.secondaryContainer.copy(
                        alpha = 0.1f
                    ) else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {




            items(userList.value) { items ->
                MCard(
                    animate = remember { mutableStateOf(true) },
                    text = items.name,
                    cardColors = items.color,
                    textColor = MaterialTheme.colorScheme.onSurface,
                    items,
                    viewModel,
                )
            }
        }
    }


//    val config =
    Row(
        modifier = Modifier
            .padding(top = 720.dp, start = 150.dp)
//                .background(Red)
            .wrapContentSize(),
    ) {
        Button(
            onClick = {
                navController.navigate(Routes.AddingScreen.route)
//                Toast.makeText(contex, "Clicked", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Add")
            Icon(Icons.Default.Add, contentDescription = null)
        }
    }
}
