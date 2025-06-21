package com.practice.roomproto.Screens

import android.util.Patterns
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.practice.roomproto.RoomDatabase.User
import com.practice.roomproto.Routes.Routes
import com.practice.roomproto.UserViewModel
import com.practice.roomproto.ui.theme.CBlue
import com.practice.roomproto.ui.theme.CDRKBLUE
import com.practice.roomproto.ui.theme.CGreen
import com.practice.roomproto.ui.theme.COrange
import com.practice.roomproto.ui.theme.CPINKING
import com.practice.roomproto.ui.theme.CPink
import com.practice.roomproto.ui.theme.CPurple
import com.practice.roomproto.ui.theme.CREDING
import com.practice.roomproto.ui.theme.CSkin
import com.practice.roomproto.ui.theme.Cpink2
import com.practice.roomproto.ui.theme.ramaGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingScreen(
    viewModel: UserViewModel,
    navController: NavHostController,
    snackBar: SnackbarHostState
) {

    val courutineScope = rememberCoroutineScope()

//    val snackBarHost = remember {
//        SnackbarHostState()
//    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Add Data")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Routes.MainScreen.route) {
                                popUpTo(0)
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBar)
        }
    ) { contentPadding ->


        Column(
            modifier = Modifier
                .padding(contentPadding)
        ) {

            var titleText by remember {
                mutableStateOf("")
            }

            var nameText by remember {
                mutableStateOf("")
            }

            var titleError by remember {
                mutableStateOf(false)
            }


            val titleEmpltySate = remember {
                mutableStateOf(false)
            }


            var nameEmpltySate by remember {
                mutableStateOf(false)
            }

            var nameError by remember {
                mutableStateOf(false)
            }
            var ageEmptyState by remember {
                mutableStateOf(false)
            }

            var ageError by remember {
                mutableStateOf(false)
            }

            var ageText by remember {
                mutableStateOf("")
            }

            var emailEmptyState by remember {
                mutableStateOf(false)
            }

            var emailError by remember {
                mutableStateOf(false)
            }

            var emailText by remember {
                mutableStateOf("")
            }

            var invalidEmail by remember {
                mutableStateOf(false)
            }

            var invalidEmailState by remember {
                mutableStateOf(false)
            }


            var phoneNoText by remember {
                mutableStateOf("")
            }

            var phoneNoError by remember {
                mutableStateOf(false)
            }

            var phoneNoEmptyState by remember {
                mutableStateOf(false)
            }

            LazyColumn(
                modifier = Modifier
                    .padding(6.dp)
                    .wrapContentSize()
            ) {
                //title
                item {


                    OutlinedTextField(

                        modifier = Modifier
                            .height(80.dp)
//                        .wrapContentSize()
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(30.dp),

                        isError = titleError,
                        value = titleText,
                        onValueChange = {
                            if (it.length <= 51) {

                                titleText = it
                                titleError = false
                            } else {
                                titleError = true

                            }

                            if (it.length >= 50) {

                                titleError = true
                            }

                            if (it.isEmpty()) {
                                titleEmpltySate.value = true
                            } else {
                                titleEmpltySate.value = false
                            }
                        },
                        label = {
                            Text(
                                "Title",
                                fontSize = 25.sp,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                        },
                        placeholder = {
                            Text("Title", fontSize = 30.sp)
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { titleText = "" }
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = null,
                                    tint = if (titleError) Red else LocalContentColor.current
                                )
                            }
                        },
                        singleLine = false,
                        leadingIcon = {
                            Icon(
                                Icons.Default.Title,
                                contentDescription = null,
                                tint = if (titleError) Red else Blue
                            )

                        },
                        textStyle = TextStyle(
                            color = LocalContentColor.current,
                            fontSize = 30.sp,
                        ),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        ),
                    )
                    AnimatedVisibility(
                        visible = titleError,
                    ) {
                        Text(
                            text = "Maxium 50Character is allowed in title or it should not be empty",
                            color = Red,
                            modifier = Modifier.padding(10.dp),
                            style = TextStyle(
                                fontSize = 12.sp
                            ),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.padding(10.dp))
                }

                //Name
                item {
                    //name
                    OutlinedTextField(
                        modifier = Modifier
//                        .clip(RoundedCornerShape(30.dp))
                            .height(80.dp)
                            .fillMaxWidth(),
                        value = nameText,
                        onValueChange = {
//                        nameText = it

                            if (it.length <= 26) {

                                nameText = it
                                nameError = false
                            } else {
                                nameError = true

                            }

                            if (it.length >= 25) {

                                nameError = true
                            }

                            if (it.isEmpty()) {
                                nameEmpltySate = true
                            } else {
                                nameEmpltySate = false
                            }
                        },
                        label = {
                            Text("Name", fontSize = 25.sp, modifier = Modifier.padding(top = 9.dp))
                        },
                        placeholder = {
                            Text("Name", fontSize = 25.sp, modifier = Modifier.padding(top = 4.dp))

                        },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = null,
                                tint = if (nameError) Red else Blue
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { nameText = "" }
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = null,
                                    tint = if (nameError) Red else LocalContentColor.current
                                )
                            }
                        },
                        shape = RoundedCornerShape(30.dp),
                        singleLine = false,
                        isError = nameError,
                        textStyle = TextStyle(
                            color = LocalContentColor.current,
                            fontSize = 30.sp,
                        ),
                        maxLines = Int.MAX_VALUE, // Allows wrapping across multiple lines

                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                            keyboardType = KeyboardType.Text

                        ),
                    )
                    AnimatedVisibility(
                        visible = nameError,
                    ) {
                        ///'
                        Text(
                            text = "Maxium 25 Character is allowed in NAME or it should not be empty",
                            color = Red,
                            modifier = Modifier.padding(10.dp),
                            style = TextStyle(
                                fontSize = 12.sp
                            ),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.padding(10.dp))
                }

                //  AGE
                item {
//                val contex = LocalContext.current
                    OutlinedTextField(
                        modifier = Modifier
//                        .clip(RoundedCornerShape(30.dp))
                            .height(80.dp)
                            .fillMaxWidth(),
                        value = ageText,
                        maxLines = Int.MAX_VALUE, // Allows wrapping across multiple lines

                        onValueChange = {
//                        nameText = it

                            if (it.length <= 3) {

                                ageText = it
                                ageError = false
                            } else {
                                ageError = true

                            }

                            if (it.length > 2) {

                                ageError = true
                            }

                            if (it.isEmpty()) {
                                ageEmptyState = true
                            } else {
                                ageEmptyState = false
                            }
                        },
                        label = {
                            Text("Age", fontSize = 25.sp, modifier = Modifier.padding(top = 9.dp))
                        },
                        placeholder = {
                            Text("Age", fontSize = 25.sp, modifier = Modifier.padding(top = 4.dp))

                        },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Message,
                                contentDescription = null,
                                tint = if (ageError) Red else Blue
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { ageText = "" }
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = null,
                                    tint = if (ageError) Red else LocalContentColor.current
                                )
                            }
                        },
                        shape = RoundedCornerShape(30.dp),
                        singleLine = false,
                        isError = ageError,
                        textStyle = TextStyle(
                            color = LocalContentColor.current,
                            fontSize = 30.sp,
                        ),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                            keyboardType = KeyboardType.Number

                        ),
//                    keyboardActions = KeyboardActions(onGo = {
//                        Toast.makeText(contex,"Nigga", Toast.LENGTH_SHORT).show()
//                    })
                    )

                    AnimatedVisibility(
                        visible = ageError,
                    ) {
                        ///'
                        Text(
                            text = "Maxium 2 Character is allowed in AGE or it should not be empty",
                            color = Red,
                            modifier = Modifier.padding(10.dp),
                            style = TextStyle(
                                fontSize = 12.sp
                            ),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(Modifier.padding(10.dp))
                }

                // EmailID
                item {


                    val emailPattern = Patterns.EMAIL_ADDRESS


                    val contex = LocalContext.current
                    OutlinedTextField(
                        modifier = Modifier
//                        .clip(RoundedCornerShape(30.dp))
                            .height(80.dp)
                            .fillMaxWidth(),
                        value = emailText,
                        maxLines = Int.MAX_VALUE, // Allows wrapping across multiple lines

                        onValueChange = {
                            invalidEmail =
                                !emailPattern.matcher(it).matches() && it.isNotEmpty() // onclick
                            emailText = it
                            if (invalidEmail) {
                                invalidEmailState = true
                            } else {
                                invalidEmailState = false
                            }
                            if (it.isEmpty()) {
                                emailEmptyState = true
                            } else {
                                emailEmptyState = false
                            }
                        },
                        label = {
                            Text("Email", fontSize = 25.sp, modifier = Modifier.padding(top = 9.dp))
                        },
                        placeholder = {
                            Text("Email", fontSize = 25.sp, modifier = Modifier.padding(top = 4.dp))

                        },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Message,
                                contentDescription = null,
                                tint = if (emailError) Red else Blue
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { emailText = "" }
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = null,
                                    tint = if (emailError) Red else LocalContentColor.current
                                )
                            }
                        },
                        shape = RoundedCornerShape(30.dp),
                        singleLine = false,
                        isError = emailError,
                        textStyle = TextStyle(
                            color = LocalContentColor.current,
                            fontSize = 30.sp,
                        ),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                            keyboardType = KeyboardType.Email
                        ),

                        )

                    AnimatedVisibility(
                        visible = emailError,
                    ) {
                        ///'

                        Text(
                            text = "Maxium 25 Character is allowed in NAME or it should not be empty",
                            color = Red,
                            modifier = Modifier.padding(10.dp),
                            style = TextStyle(
                                fontSize = 12.sp
                            ),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )


                    }

                }

                // Phone No
                item {
                    Spacer(Modifier.padding(10.dp))


                    // PhoneNo
                    OutlinedTextField(
                        modifier = Modifier
                            .height(80.dp)
                            .fillMaxWidth(),
                        value = phoneNoText,
                        onValueChange = {
//                        nameText = it

                            if (it.length <= 11) {

                                phoneNoText = it
                                phoneNoError = false
                            } else {
                                phoneNoError = true

                            }

                            if (it.length > 10) {

                                phoneNoError = true
                            }

                            if (it.isEmpty()) {
                                phoneNoEmptyState = true
                            } else {
                                phoneNoEmptyState = false
                            }
                        },
                        label = {
                            Text(
                                "Phone No",
                                fontSize = 25.sp,
                                modifier = Modifier.padding(top = 9.dp)
                            )
                        },
                        maxLines = Int.MAX_VALUE, // Allows wrapping across multiple lines

                        placeholder = {
                            Text(
                                "Phone No",
                                fontSize = 25.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                        },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Call,
                                contentDescription = null,
                                tint = if (phoneNoError) Red else Blue
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { phoneNoText = "" }
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = null,
                                    tint = if (phoneNoError) Red else LocalContentColor.current
                                )
                            }
                        },
                        shape = RoundedCornerShape(30.dp),
                        singleLine = false,
                        isError = phoneNoError,
                        textStyle = TextStyle(
                            color = LocalContentColor.current,
                            fontSize = 30.sp,
                        ),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                            keyboardType = KeyboardType.Phone

                        ),

                        suffix = {
                            Text("+91")
                        }
                    )
                    AnimatedVisibility(
                        visible = phoneNoError,
                    ) {
                        ///'
                        Text(
                            text = "invalid Phone No",
                            color = Red,
                            modifier = Modifier.padding(10.dp),
                            style = TextStyle(
                                fontSize = 12.sp
                            ),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }


                // COlor login and Button onClick
                item {


                    Spacer(Modifier.padding(20.dp))
//                    var randomNudmber by remember { mutableStateOf(0) }
//                    val randomNumber = (1..5).random()

                    var randomNumber by remember { mutableStateOf((1..5).random()) }

                    var cardColor by remember {
                        mutableLongStateOf(0xFF6650a4)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Button(
                            modifier = Modifier,
                            onClick = {
                                /// mainContnet

                                randomNumber = (1..11).random()
//                                Log.d("Random No Nigga", "Random No$randomNumber")
//                                navController.navigate(Routes.MainScreen.route)
                                when (randomNumber) {
                                    1 -> {
                                        cardColor = CPink
                                    }

                                    2 -> {
                                        cardColor = CGreen
                                    }

                                    3 -> {
                                        cardColor = CSkin
                                    }

                                    4 -> {
                                        cardColor = COrange
                                    }

                                    5 -> {
                                        cardColor = CPurple

                                    }

                                    6 -> {
                                        cardColor = CBlue
                                    }

                                    7 -> {
                                        cardColor = CREDING
                                    }

                                    8 -> {
                                        cardColor = CPINKING
                                    }

                                    9 -> {
                                        cardColor = CDRKBLUE
                                    }

                                    10 -> {
                                        cardColor = ramaGreen
                                    }

                                    11 -> {
                                        cardColor = Cpink2
                                    }
                                }

                                viewModel.upersrinUser(
                                    User(
                                        name = nameText,
                                        email = emailText,
                                        title = titleText,
                                        age = ageText.toInt(),
                                        phoneNo = phoneNoText,
                                        color = cardColor
                                    )
                                )

                                navController.navigate(Routes.MainScreen.route) {
                                    popUpTo(0)
                                }
                            },

                            ) {
                            Text("Add Data", fontSize = 20.sp)
                        }
                    }

                }
            }
        }

    }

}


