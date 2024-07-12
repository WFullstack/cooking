package com.berakahnd.cookingreceipe.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.berakahnd.cookingreceipe.data.local.FavCookingReceipe
import com.berakahnd.cookingreceipe.ui.screen.AppNavHost
import com.berakahnd.cookingreceipe.ui.theme.CookingReceipeTheme
import com.berakahnd.cookingreceipe.ui.viewmodel.CookingViewModel
import com.berakahnd.cookingreceipe.ui.viewmodel.FavViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CookingReceipeTheme {
                val cookingViewmodel : CookingViewModel = hiltViewModel()
                val favViewmodel : FavViewModel = hiltViewModel()
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(
                        cookingviewModel = cookingViewmodel,
                        favViewmodel = favViewmodel,
                        navController = navController,
                    )
                }
            }
        }
    }
}
