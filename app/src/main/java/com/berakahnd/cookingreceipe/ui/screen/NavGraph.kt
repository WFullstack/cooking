package com.berakahnd.cookingreceipe.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.berakahnd.cookingreceipe.ui.viewmodel.CookingViewModel
import com.berakahnd.cookingreceipe.ui.viewmodel.FavViewModel

@Composable
fun AppNavHost(
    cookingviewModel : CookingViewModel,
    favViewmodel : FavViewModel,
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(
                navController = navController
            )
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(cookingviewModel = cookingviewModel, favViewmodel = favViewmodel, navController = navController)
        }
        composable(NavigationItem.Details.route) {
            DetailsScreen(cookingviewModel = cookingviewModel, favViewmodel = favViewmodel, navController = navController)
        }
        composable(NavigationItem.Favorite.route) {
            FavoriteScreen(cookingviewModel = cookingviewModel,favViewmodel = favViewmodel, navController = navController)
        }
    }
}
enum class Screen {
    SPLASH,
    HOME,
    FAVORITE,
    DETAILS,
}
sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Home : NavigationItem(Screen.HOME.name)
    object Favorite : NavigationItem(Screen.FAVORITE.name)
    object Details : NavigationItem(Screen.DETAILS.name)
}