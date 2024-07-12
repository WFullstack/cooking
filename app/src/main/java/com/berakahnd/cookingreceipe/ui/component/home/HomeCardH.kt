package com.berakahnd.cookingreceipe.ui.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.berakahnd.cookingreceipe.data.local.FavCookingReceipe
import com.berakahnd.cookingreceipe.data.model.CookingModelItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeCardH(
    modifier : Modifier = Modifier,
    item : CookingModelItem,
    onFavoritemClick : (FavCookingReceipe) -> Unit,
    goToDetailScreenClick : (CookingModelItem) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp * 80/ 100
    val width = (screenWidth / 4)

    val fav = FavCookingReceipe(continent = item.continent, cookingSteps = item.cookingSteps,
        cookingTime = item.cookingTime, imageUrl = item.imageUrl, ingredients = item.ingredients,
        receipeName = item.receipeName
    )
    Card(
        modifier = modifier.width(screenWidth),
        onClick = { goToDetailScreenClick(item) }) {
        Row(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween ) {
            Box(
                modifier = modifier
                    .size(width)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ){
                AsyncImage(modifier = modifier
                    .padding(5.dp).size(width)
                    .clip(CircleShape), alignment = Alignment.Center,
                    contentScale  = ContentScale.Crop,
                    model = item.imageUrl, contentDescription = null)
            }
            Spacer(modifier = modifier.size(8.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "${item.receipeName}",fontSize = 18.sp,fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Clip,
                    softWrap = true,maxLines = 1)
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "${item.cookingTime}",color = MaterialTheme.colorScheme.onSurfaceVariant)

                    IconButton(onClick = {
                        onFavoritemClick(fav)
                    }) {
                        Icon(imageVector = Icons.Default.BookmarkAdd, contentDescription = null)
                    }
                }
            }
        }
    }
}
