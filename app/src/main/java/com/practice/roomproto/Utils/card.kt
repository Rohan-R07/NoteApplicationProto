package com.practice.roomproto.Utils

import android.annotation.SuppressLint
import android.util.Patterns
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseOutQuint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.practice.roomproto.RoomDatabase.User
import com.practice.roomproto.UserViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun MCard(
    animate: MutableState<Boolean>,
    text: String,
    cardColors: Long = 0xFF6650a4,
    textColor: Color = Black,
    item: User,
    viewModel: UserViewModel,
) {


    val roundedCornerAnimateion = animateDpAsState(
        targetValue = if (animate.value) 20.dp else 100.dp, label = "dd", animationSpec = tween(
            durationMillis = 1000, delayMillis = 100, easing = EaseOutQuint
        )
    )

    val couritineScope = rememberCoroutineScope()

    val safeCorner = max(roundedCornerAnimateion.value, 0.dp)

    AnimatedVisibility(
        visible = animate.value,
        modifier = Modifier.clip(shape = RoundedCornerShape(safeCorner)),
        enter = expandIn() + fadeIn(),
        exit = shrinkOut() + fadeOut(),
    ) {
        val config = LocalConfiguration.current
        val screenWidth = config.screenWidthDp
        var bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        var bottmSheetClickState = remember {
            mutableStateOf(false)
        }
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .width(if (screenWidth < 400) 330.dp else 360.dp)
                .height(100.dp)
                .clickable {
                    bottmSheetClickState.value = !bottmSheetClickState.value
                }, colors = CardColors(
                containerColor = Color(cardColors),
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContentColor = Red
            )
        ) {
            if (bottmSheetClickState.value) {
                ModalBottomSheet(
                    onDismissRequest = {
                        bottmSheetClickState.value = !bottmSheetClickState.value
                    },
                    sheetState = bottomSheetState,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,

                    ) {
                    var titleText by remember {
                        mutableStateOf("")
                    }
                    var titleError by remember {
                        mutableStateOf(false)
                    }

                    var nameText by remember {
                        mutableStateOf("")
                    }
                    var nameError by remember {
                        mutableStateOf(false)
                    }

                    var ageText by remember {
                        mutableStateOf("")
                    }

                    var ageError by remember {
                        mutableStateOf(
                            false
                        )
                    }
                    var emailText by remember {
                        mutableStateOf("")
                    }

                    var emailError by remember {
                        mutableStateOf(false)
                    }

                    var invalidEmailState by remember {
                        mutableStateOf(false)
                    }

                    var invalidEmail by remember {
                        mutableStateOf(false)
                    }

                    var phoneNoText by remember {
                        mutableStateOf(item.phoneNo)
                    }
                    var phoneNoError by remember {
                        mutableStateOf(false)
                    }
                    var phoneNoEmptyState by remember {
                        mutableStateOf(false)
                    }
                    val contex = LocalContext.current

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        // title text
                        item {
                            //name
                            OutlinedTextField(
                                modifier = Modifier
//                        .clip(RoundedCornerShape(30.dp))
                                    .height(80.dp)
                                    .fillMaxWidth(),
                                value = titleText,
                                onValueChange = {

                                    if (it.length <= 26) {

                                        titleText = it
                                        titleError = false
                                    } else {
                                        titleError = true

                                    }

                                    if (it.length >= 25) {

                                        titleError = true
                                    }


                                },
                                label = {
                                    Text(
                                        "Title",
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(top = 9.dp)
                                    )
                                },
                                placeholder = {
                                    Text(
                                        "Title",
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )

                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Filled.Person,
                                        contentDescription = null,
                                        tint = if (titleError) Red else Blue
                                    )
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
                                shape = RoundedCornerShape(30.dp),
                                singleLine = false,
                                isError = titleError,
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
                                visible = titleError,
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

                        // Name
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


                                },
                                label = {
                                    Text(
                                        "Name",
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(top = 9.dp)
                                    )
                                },
                                placeholder = {
                                    Text(
                                        "Name",
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )

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

                        //AGe
                        item {
//                val contex = LocalContext.current
                            OutlinedTextField(
                                modifier = Modifier
//                        .clip(RoundedCornerShape(30.dp))
                                    .height(80.dp)
                                    .fillMaxWidth(),
                                value = ageText.toString(),
                                maxLines = Int.MAX_VALUE, // Allows wrapping across multiple lines

                                onValueChange = {
//                        nameText = it

                                    if (it.length <= 3) {

                                        ageText = it.toString()
                                        ageError = false
                                    } else {
                                        ageError = true

                                    }

                                    if (it.length > 2) {

                                        ageError = true
                                    }

                                },
                                label = {
                                    Text(
                                        "Age",
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(top = 9.dp)
                                    )
                                },
                                placeholder = {
                                    Text(
                                        "Age",
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )

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

                        // email Text
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
                                        !emailPattern.matcher(it)
                                            .matches() && it.isNotEmpty() // onclick
                                    emailText = it
                                    if (invalidEmail) {
                                        invalidEmailState = true
                                    } else {
                                        invalidEmailState = false
                                    }

                                },
                                label = {
                                    Text(
                                        "Email",
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(top = 9.dp)
                                    )
                                },
                                placeholder = {
                                    Text(
                                        "Email",
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )

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
                        item {
                            Spacer(Modifier.padding(top = 10.dp))
                            Button(
                                onClick = {
//                                    viewModel.updateUser(
//                                        item,
//                                        nameText,
//                                        emailText,
//                                        phoneNoText
//                                    )
                                    val updatedData = item.copy(
                                        name = nameText,
                                        email = emailText,
                                        phoneNo = phoneNoText
                                    )

                                    Toast.makeText(contex, item.name, Toast.LENGTH_SHORT).show()

                                    viewModel.upersrinUser(updatedData)
                                },
                                modifier = Modifier
                            ) {
                                Text("Update")
                            }
                        }

                    }
                }
            }
            Row {
                val date = Date(item.currentDate)
                val formatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
                val formattedDate = formatter.format(date)

                Box(
                    modifier = Modifier
                        .width(370.dp)
                        .height(100.dp),
                ) {
                    // Always title
                    Text(
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp,
                        maxLines = 2,
                        text = text,
                        style = MaterialTheme.typography.titleLarge,
                        color = textColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 120.dp, top = 40.dp)

                    )
                    Text(
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp,
                        text = formattedDate,
                        style = MaterialTheme.typography.bodySmall,
                        color = textColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 80.dp, start = 190.dp),

                        )
                    IconButton(
                        modifier = Modifier
                            .padding(start = 275.dp, top = 24.dp),
                        onClick = {
                            // onClick function
                            couritineScope.launch {
                                viewModel.deleteUser(item)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete, contentDescription = null
                        )

                    }
                }
            }
        }
    }
}
