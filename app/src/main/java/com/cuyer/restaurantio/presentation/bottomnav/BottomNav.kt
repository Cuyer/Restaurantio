package com.cuyer.restaurantio.presentation.bottomnav

import android.provider.MediaStore
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cuyer.restaurantio.presentation.composables.ButtonWithImage
import com.cuyer.restaurantio.presentation.composables.Credentials
import com.cuyer.restaurantio.presentation.composables.StandardButton

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
            ProfileScreen()
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
fun ProfileScreen() {

    ConstraintLayout(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
    ) {
        val(backButton, signUpTextField, textField, googleButton, appleButton,
            credentialsName, credentialsEmail, credentialsPassword, credentialsNameText,
            credentialsEmailText, credentialsPasswordText,
            createAccountButton, textField2 ) = createRefs()
        createHorizontalChain(googleButton,appleButton, chainStyle = ChainStyle.Packed)

        OutlinedButton(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(backButton) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        ) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back Button")
        }

        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text("Zarejestruj się", Modifier.constrainAs(signUpTextField) {
            start.linkTo(backButton.end, margin = 16.dp)
            top.linkTo(backButton.top)
            bottom.linkTo(backButton.bottom)
        })

        Text(
            text = "Zarejestruj się używając jednej z poniższych opcji.",
            modifier = Modifier
                .constrainAs(textField) {
                    start.linkTo(parent.start)
                    top.linkTo(backButton.bottom, margin = 20.dp)
                }
        )

        OutlinedButton(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(googleButton) {
                top.linkTo(textField.bottom)
                width = Dimension.fillToConstraints
            }
        ) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back Button")
        }

        OutlinedButton(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(appleButton) {
                top.linkTo(textField.bottom)
                width = Dimension.fillToConstraints
            }
        ) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back Button")
        }

        var text by remember { mutableStateOf(TextFieldValue("")) }

        Text(
            modifier = Modifier
                .constrainAs(credentialsNameText)
                {
                    bottom.linkTo(credentialsName.top)
                    start.linkTo(parent.start)
                    top.linkTo(googleButton.bottom)

                }
                .fillMaxWidth()
                .padding(25.dp, 0.dp, 20.dp, 5.dp),
            text = "Name")

        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText},
            placeholder = { Text(text = "Enter your name") },
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
        )



        Text(
            modifier = Modifier
                .constrainAs(credentialsEmailText)
                {
                    top.linkTo(credentialsName.bottom)
                    start.linkTo(parent.start)


                }
                .fillMaxWidth()
                .padding(25.dp, 0.dp, 20.dp, 5.dp),
            text = "Email")

        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText},
            placeholder = { Text(text = "Enter your email") },
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
        )



        Text(
            modifier = Modifier
                .constrainAs(credentialsPasswordText)
                {
                    start.linkTo(parent.start)
                    top.linkTo(credentialsEmail.bottom)

                }
                .fillMaxWidth()
                .padding(25.dp, 0.dp, 20.dp, 5.dp),
            text = "Password")

        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText},
            placeholder = { Text(text = "Enter your password") },
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
        )

        Button(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(createAccountButton) {
                top.linkTo(credentialsPassword.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Stwórz konto")
        }

        Text(
            text = "Masz już konto? Zaloguj się",
            modifier = Modifier
                .constrainAs(textField2) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(createAccountButton.bottom)
                }
        )




    }
}