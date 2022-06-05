package com.cuyer.restaurantio.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.rememberNavController
import com.cuyer.restaurantio.presentation.bottomnav.BottomNavItem
import com.cuyer.restaurantio.presentation.bottomnav.BottomNavigationBar
import com.cuyer.restaurantio.presentation.bottomnav.Navigation
import com.cuyer.restaurantio.presentation.ui.theme.RestaurantioTheme
import com.cuyer.restaurantio.domain.viewmodels.MainActivityViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            RestaurantioTheme {
                Surface(Modifier.fillMaxSize()) {

                    val navController = rememberNavController()
                    Scaffold(
                        content = {padding ->
                            Column(
                                modifier = Modifier.padding(padding)
                            ) {
                                Navigation(navController = navController)
                            }
                        },
                        bottomBar = {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem(
                                        name = "Home",
                                        route = "home",
                                        icon = Icons.Outlined.Home
                                    ),
                                    BottomNavItem(
                                        name = "Orders",
                                        route = "orders",
                                        icon = Icons.Outlined.ShoppingBag
                                    ),
                                    BottomNavItem(
                                        name = "Map",
                                        route = "map",
                                        icon = Icons.Outlined.Map
                                    ),
                                    BottomNavItem(
                                        name = "Profile",
                                        route = "profile",
                                        icon = Icons.Outlined.Person
                                    ),


                                ),

                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )

                        }
                    )

               }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RestaurantioTheme {

    }
}