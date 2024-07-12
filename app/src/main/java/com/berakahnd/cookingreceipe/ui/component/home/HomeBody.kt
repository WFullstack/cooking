package com.berakahnd.cookingreceipe.ui.component.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.berakahnd.cookingreceipe.data.local.FavCookingReceipe
import com.berakahnd.cookingreceipe.data.model.CookingModel
import com.berakahnd.cookingreceipe.data.model.CookingModelItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBody(
    goToDetailScreenClick : (CookingModelItem) -> Unit,
    onFavoritemClick : (FavCookingReceipe) -> Unit, datas: CookingModel
) {
    val dataContinent = datas.distinctBy { it.continent }
    val dataHorizontal = datas.asReversed()
    var search by rememberSaveable { mutableStateOf("") }

    Column(
        Modifier.padding(horizontal = 16.dp),
        //verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = "Cooking Recipe", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text(text = "become a chef with our recipes", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    TextField(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent, disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        placeholder = { Text(text = "Search....") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        value = search,
        onValueChange = {
            search = it
        }
    )
    Text(modifier = Modifier.padding(horizontal = 16.dp),text = "Category", fontSize = 18.sp)
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(dataContinent){continent ->
            OutlinedCard( shape = RoundedCornerShape(24.dp)) {
                Text( modifier = Modifier.padding(16.dp),text = continent.continent)
            }
        }
    }
    Text(modifier = Modifier.padding(horizontal = 16.dp),text = "List cooking recipe", fontSize = 18.sp)
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(datas){cookingModelItem ->
            HomeCardV(
                item = cookingModelItem,
                onFavoritemClick = { fav ->
                    Log.i("FAV",fav.toString())
                    onFavoritemClick(fav)
                },
                goToDetailScreenClick = { cookingItem ->
                    goToDetailScreenClick(cookingItem)
                }
            )
        }
    }
    Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Popular Food", fontSize = 18.sp, )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(dataHorizontal){cookingModelItem ->
            HomeCardH(
                item = cookingModelItem,
                onFavoritemClick = {fav ->
                    Log.i("FAV",fav.toString())
                    onFavoritemClick(fav)
                },
                goToDetailScreenClick = { cookingItem ->
                    goToDetailScreenClick(cookingItem)
                }
            )
        }
    }
}


