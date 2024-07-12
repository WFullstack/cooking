package com.berakahnd.cookingreceipe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.berakahnd.cookingreceipe.ui.component.home.HomeBody
import com.berakahnd.cookingreceipe.ui.component.home.HomeBodySkeleton
import com.berakahnd.cookingreceipe.ui.viewmodel.CookingViewModel
import com.berakahnd.cookingreceipe.ui.viewmodel.FavViewModel

@Composable
fun HomeScreen(
    cookingviewModel : CookingViewModel,
    favViewmodel : FavViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    var openAlertDialog by rememberSaveable { mutableStateOf(false) }

    var state = cookingviewModel.cookingUiState.value
    Scaffold(
        topBar = {
            HomeTopBar(
                goToFaviteScreenClick = {
                    navController.navigate(Screen.FAVORITE.name)
                }
            )
        }
    ) {
        Column(
            modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            if(state.isLoading){
                HomeBodySkeleton()
            }else
                if(state.error.isNotEmpty()){
                    //openAlertDialog = true
                    HomeBodySkeleton()
                }else{
                    HomeBody(
                        goToDetailScreenClick = { cook ->
                            cookingviewModel.sendCooking(cook)
                            navController.navigate(Screen.DETAILS.name)
                        }, onFavoritemClick = { fav ->
                            favViewmodel.insertFav(fav)
                            Toast.makeText(context,"${fav.receipeName} has been added!",Toast.LENGTH_LONG).show()
                        },
                        datas = state.data
                    )
                }
        }
    }
    if(openAlertDialog) {
        HomeAlertDialog(
            onDismissRequest = { openAlertDialog = false },
            onConfirmation = { openAlertDialog = false },
            dialogTitle = "Error",
            dialogText = "An error has occurred, check the internet connection.",
            icon = Icons.Default.Error
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(goToFaviteScreenClick : () -> Unit ) {
    TopAppBar(title = { /*TODO*/ }, navigationIcon = {
        Icon(imageVector = Icons.Default.RestaurantMenu, contentDescription = null)
    }, actions = {
        IconButton(onClick = {
            goToFaviteScreenClick()
        }) {
            Icon(imageVector = Icons.Default.Bookmark, contentDescription = null)
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}