package com.cuyer.restaurantio.presentation.bottomnav

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cuyer.restaurantio.domain.viewmodels.MainActivityViewModel
import com.cuyer.restaurantio.presentation.screens.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun Navigation (navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home",  ) {
        var isLogged by mutableStateOf(false)

        if (Firebase.auth.currentUser != null) {
            isLogged = true
        } else {
            isLogged = false
        }

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
            if(isLogged == false) {
                RegisterScreen(
                    onItemClick = { navController.navigate("login") },
                    onRegisterClick = {
                        navController.popBackStack()
                        navController.navigate("profile")
                        } )
            } else {
                ProfileScreen()
            }

        }

        composable("login") {
                LoginScreen(onItemClick = {
                    navController.navigate("profile")
                }, onBackClick = {
                    navController.navigate("profile")
                }, onLogInClick = {
                    navController.popBackStack()
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

