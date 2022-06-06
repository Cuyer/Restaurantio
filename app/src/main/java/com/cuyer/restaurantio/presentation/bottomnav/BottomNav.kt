package com.cuyer.restaurantio.presentation.bottomnav

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cuyer.restaurantio.R
import com.cuyer.restaurantio.domain.viewmodels.AuthenticationViewModel
import com.cuyer.restaurantio.presentation.screens.LoginScreen


@Composable
fun Navigation (navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home" ) {

        composable("home") {
            HomeScreen()
        }

        composable("orders") {
            OrdersScreen()
        }

        composable("map") {
            MapScreen()
        }


        composable("profile") {
            ProfileScreen(onItemClick = {
                navController.navigate("login")
            })

        }

        composable("login") {
                LoginScreen(onItemClick = {
                    navController.navigate("profile")
                }, onBackClick = {
                    navController.navigate("profile")
                })
        }
    }
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    if (isSystemInDarkTheme()) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.Black,
        elevation = 8.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                unselectedContentColor = Color.Gray,
                selectedContentColor = Color.White,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }

                }
            )
        }
     }
  } else {
        val backStackEntry = navController.currentBackStackEntryAsState()
        BottomNavigation(
            modifier = modifier,
            backgroundColor = Color.White,
            elevation = 8.dp
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Gray,
                    icon = {
                        Column(horizontalAlignment = CenterHorizontally) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                            if (selected) {
                                Text(
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp
                                )
                            }
                        }

                    }
                )
            }
        }
    }
}


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home screen")
    }
}

@Composable
fun OrdersScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Orders")
    }
}

@Composable
fun MapScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Map")
    }
}

@Composable
fun ProfileScreen(onItemClick: (Int) -> Unit,
    viewModel: AuthenticationViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val focusManager = LocalFocusManager.current

    ConstraintLayout(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
    ) {
        val(signUpTextField, textField, googleButton, appleButton,
            credentialsName, credentialsEmail, credentialsPassword, credentialsNameText,
            credentialsEmailText, credentialsPasswordText,
            createAccountButton, textField2, appendedString ) = createRefs()
        createHorizontalChain(googleButton,appleButton, chainStyle = ChainStyle.Packed)


        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text(
            text = "Zarejestruj się",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .constrainAs(signUpTextField) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start, margin = 20.dp)
        })

        Text(
            text = "Zarejestruj się używając jednej z poniższych opcji.",
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(textField) {
                    start.linkTo(parent.start, margin = 20.dp)
                    top.linkTo(signUpTextField.bottom, margin = 60.dp)
                }

        )
        if (isSystemInDarkTheme()) {
            OutlinedButton(
                onClick = { /* Do something */ },
                // Assign reference "button" to the Button composable
                // and constrain it to the top of the ConstraintLayout
                modifier = Modifier
                    .padding(20.dp, 0.dp, 5.dp, 0.dp)
                    .constrainAs(googleButton) {
                        top.linkTo(textField.bottom, 20.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.value(50.dp)
                    },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF181818)
                )

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_icon),
                    contentDescription = "Google Icon",
                    tint = Color.White
                )
            }
        } else {
            OutlinedButton(
                onClick = { /* Do something */ },
                // Assign reference "button" to the Button composable
                // and constrain it to the top of the ConstraintLayout
                modifier = Modifier
                    .padding(20.dp, 0.dp, 5.dp, 0.dp)
                    .constrainAs(googleButton) {
                        top.linkTo(textField.bottom, 20.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.value(50.dp)
                    },
                shape = RoundedCornerShape(10.dp),


            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_icon),
                    contentDescription = "Google Icon",
                    tint = Color.Black
                )
            }
        }

        if (isSystemInDarkTheme()) {
            OutlinedButton(
                onClick = { /* Do something */ },

                // Assign reference "button" to the Button composable
                // and constrain it to the top of the ConstraintLayout
                modifier = Modifier
                    .padding(5.dp, 0.dp, 20.dp, 0.dp)
                    .constrainAs(appleButton) {
                        top.linkTo(textField.bottom, 20.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.value(50.dp)
                    },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF181818)
                )

            ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_apple_logo),
                        contentDescription = "Apple Icon",
                        tint = Color.White
                    )
                }
            } else {
            OutlinedButton(
                onClick = { /* Do something */ },

                // Assign reference "button" to the Button composable
                // and constrain it to the top of the ConstraintLayout
                modifier = Modifier
                    .padding(5.dp, 0.dp, 20.dp, 0.dp)
                    .constrainAs(appleButton) {
                        top.linkTo(textField.bottom, 20.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.value(50.dp)
                    },
                shape = RoundedCornerShape(10.dp),


            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_apple_logo),
                    contentDescription = "Apple Icon",
                    tint = Color.Black
                )
            }
        }


        Text(
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .constrainAs(credentialsNameText)
                {
                    bottom.linkTo(credentialsName.top)
                    start.linkTo(parent.start)
                    top.linkTo(googleButton.bottom, 30.dp)

                }
                .fillMaxWidth()
                .padding(25.dp, 0.dp, 20.dp, 5.dp),
            text = "Imię")

        if (isSystemInDarkTheme()) {
            OutlinedTextField(
                value = viewModel.nameAuth,
                onValueChange = { viewModel.nameAuth = it},
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFF181818)
                ),
                placeholder = { Text(
                    text = "Wpisz swoje imię",
                    style = MaterialTheme.typography.h3,) },
                modifier = Modifier
                    .constrainAs(credentialsName)
                    {
                        top.linkTo(credentialsNameText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
        } else {
            OutlinedTextField(
                value = viewModel.nameAuth,
                onValueChange = { viewModel.nameAuth = it},
                singleLine = true,
                placeholder = { Text(
                    text = "Wpisz swoje imię",
                    style = MaterialTheme.typography.h3,) },
                modifier = Modifier
                    .constrainAs(credentialsName)
                    {
                        top.linkTo(credentialsNameText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
        }




        Text(
            modifier = Modifier
                .constrainAs(credentialsEmailText)
                {
                    top.linkTo(credentialsName.bottom, 20.dp)
                    start.linkTo(parent.start)


                }
                .fillMaxWidth()
                .padding(25.dp, 0.dp, 20.dp, 5.dp),
            text = "Email",
            style = MaterialTheme.typography.h3,)

        if (isSystemInDarkTheme()) {
            OutlinedTextField(
                value = viewModel.emailAuth,
                onValueChange = { viewModel.emailAuth = it },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFF181818)
                ),
                placeholder = { Text(
                    text = "Wpisz swój email",
                    style = MaterialTheme.typography.h3,) },
                modifier = Modifier
                    .constrainAs(credentialsEmail)
                    {
                        top.linkTo(credentialsEmailText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
        } else {
            OutlinedTextField(
                value = viewModel.emailAuth,
                onValueChange = { viewModel.emailAuth = it },
                singleLine = true,
                placeholder = { Text(
                    text = "Wpisz swój email",
                    style = MaterialTheme.typography.h3,) },
                modifier = Modifier
                    .constrainAs(credentialsEmail)
                    {
                        top.linkTo(credentialsEmailText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
        }




        Text(
            modifier = Modifier
                .constrainAs(credentialsPasswordText)
                {
                    start.linkTo(parent.start)
                    top.linkTo(credentialsEmail.bottom, 20.dp)

                }
                .fillMaxWidth()
                .padding(25.dp, 0.dp, 20.dp, 5.dp),
            text = "Hasło",
            style = MaterialTheme.typography.h3,)

        if (isSystemInDarkTheme()) {
            val trailingIconView = @Composable {
                IconToggleButton(
                    checked = viewModel.passIconChecked,
                    onCheckedChange = {
                        viewModel.passIconChecked = it
                    }) {
                    if (viewModel.passIconChecked) {
                        Icon(imageVector = Icons.Outlined.VisibilityOff, contentDescription = "")

                    } else {
                        Icon(imageVector = Icons.Outlined.Visibility, contentDescription = "")
                    }
                }
            }

            OutlinedTextField(
                value = viewModel.passAuth,
                onValueChange = { viewModel.passAuth = it},
                singleLine = true,
                trailingIcon = if (viewModel.passAuth.isNotEmpty()) trailingIconView else null,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFF181818)
                ),
                placeholder = { Text(
                    text = "Wpisz swoje hasło ",
                    style = MaterialTheme.typography.h3,) },
                modifier = Modifier
                    .constrainAs(credentialsPassword)
                    {
                        top.linkTo(credentialsPasswordText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = if (viewModel.passIconChecked) PasswordVisualTransformation()
                else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        } else {
            val trailingIconView = @Composable {
                IconToggleButton(
                    checked = viewModel.passIconChecked,
                    onCheckedChange = {
                        viewModel.passIconChecked = it
                    }) {
                    if (viewModel.passIconChecked) {
                        Icon(imageVector = Icons.Outlined.Visibility, contentDescription = "")
                    } else {
                        Icon(imageVector = Icons.Outlined.VisibilityOff, contentDescription = "")
                    }
                }
            }
            OutlinedTextField(
                value = viewModel.passAuth,
                onValueChange = { viewModel.passAuth = it},
                singleLine = true,
                trailingIcon = if (viewModel.passAuth.isNotEmpty()) trailingIconView else null,
                placeholder = { Text(
                    text = "Wpisz swoje hasło ",
                    style = MaterialTheme.typography.h3,) },
                modifier = Modifier
                    .constrainAs(credentialsPassword)
                    {
                        top.linkTo(credentialsPasswordText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = if (viewModel.passIconChecked) PasswordVisualTransformation()
                else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }

        if (
            viewModel.nameAuth.isNotEmpty() &&
            viewModel.emailAuth.isNotEmpty() &&
            viewModel.passAuth.isNotEmpty()
        ) {
            Button(
                onClick = { viewModel.authValidation() },
                // Assign reference "button" to the Button composable
                // and constrain it to the top of the ConstraintLayout
                modifier = Modifier
                    .constrainAs(createAccountButton) {
                        top.linkTo(credentialsPassword.bottom, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
            ) {
                Text(text = "Stwórz konto",
                    color = Color.White,
                    style = MaterialTheme.typography.button,)
            }
        } else {

            Button(
                onClick = { viewModel.authValidation() },
                // Assign reference "button" to the Button composable
                // and constrain it to the top of the ConstraintLayout
                modifier = Modifier
                    .constrainAs(createAccountButton) {
                        top.linkTo(credentialsPassword.bottom, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
            ) {
                Text(text = "Stwórz konto",
                    color = Color.White,
                    style = MaterialTheme.typography.button,)
            }
        }


        Row(modifier = Modifier
            .constrainAs(textField2) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(createAccountButton.bottom, 20.dp)
            },
                verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Masz już konto? ",
                style = MaterialTheme.typography.body2,

                )

            ClickableText(
                text = AnnotatedString("Zaloguj się", spanStyle = SpanStyle(
                    color = MaterialTheme.colors.primary
                )
                ),
                onClick = onItemClick,
                style = MaterialTheme.typography.body2,
            )
        }


    }
}

