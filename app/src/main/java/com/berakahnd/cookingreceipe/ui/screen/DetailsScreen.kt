package com.berakahnd.cookingreceipe.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.berakahnd.cookingreceipe.ui.component.details.DetailsBody
import com.berakahnd.cookingreceipe.ui.viewmodel.CookingViewModel
import com.berakahnd.cookingreceipe.ui.viewmodel.FavViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    cookingviewModel : CookingViewModel,
    favViewmodel : FavViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
){
    val cookingModelItem = cookingviewModel.cookingModelItem.value
    Scaffold(
        topBar = {
            TopAppBar(title = { /*TODO*/ }, navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            })
        }
    ) { innerPadding ->
        Column(
            modifier.fillMaxSize().padding(innerPadding).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            DetailsBody(cookingModelItem = cookingModelItem)
        }
    }
}

