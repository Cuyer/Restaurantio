package com.cuyer.restaurantio.presentation.bottomnav

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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


@Composable
fun Navigation (navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home",  ) {


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
            if(MainActivityViewModel().isUserLoggedIn == false) {
                RegisterScreen(onItemClick = { navController.navigate("login") } )
            } else {
                ProfileScreen()
            }

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

