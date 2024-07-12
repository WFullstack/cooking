package com.berakahnd.cookingreceipe.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.berakahnd.cookingreceipe.data.local.FavCookingReceipe
import com.berakahnd.cookingreceipe.data.model.CookingModelItem
import com.berakahnd.cookingreceipe.ui.viewmodel.CookingViewModel
import com.berakahnd.cookingreceipe.ui.viewmodel.FavViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    cookingviewModel : CookingViewModel,
    favViewmodel : FavViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val favcooking = favViewmodel.favUiState.value.data
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
    ) {
        Column(
            modifier.fillMaxSize().padding(it).padding(horizontal = 16.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(text = "List of favorites", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            if(favcooking.isEmpty()){
                Text(text = "Favorites list is empty", fontSize = 18.sp)
            }
            favcooking.forEachIndexed { index, fav ->
                FavCardItem( modifier  = Modifier, favCookingReceipe = fav, onDeleteClick = { favCookingReceipe ->
                    favViewmodel.deleteFav(favCookingReceipe = favCookingReceipe)
                    Toast.makeText(context,"${fav.receipeName} has been deleted!", Toast.LENGTH_LONG).show()
                }, onDetailClick = { item ->
                    val cook = CookingModelItem(continent = item.continent, cookingSteps = item.cookingSteps,
                        cookingTime = item.cookingTime, imageUrl = item.imageUrl, ingredients = item.ingredients,
                        receipeName = item.receipeName
                    )
                    cookingviewModel.sendCooking(cook)
                    navController.navigate(Screen.DETAILS.name)
                })
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavCardItem(
    modifier : Modifier = Modifier,
    favCookingReceipe: FavCookingReceipe,
    onDeleteClick : (FavCookingReceipe) -> Unit,
    onDetailClick : (FavCookingReceipe) -> Unit
){
    val configuration = LocalConfiguration.current
    val screenImage = configuration.screenWidthDp.dp * 15 /100

    Card(onClick = { onDetailClick(favCookingReceipe) }) {
        Row(modifier = modifier.fillMaxWidth(), Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(modifier = Modifier.padding(8.dp).size(screenImage).clip(CircleShape),
                alignment = Alignment.Center, contentScale  = ContentScale.Crop, model = favCookingReceipe.imageUrl,
                contentDescription = null)
            Text(text = favCookingReceipe.receipeName, fontWeight = FontWeight.Bold)
            IconButton(onClick = {
                onDeleteClick(favCookingReceipe)
            }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardItemPreview(){

}