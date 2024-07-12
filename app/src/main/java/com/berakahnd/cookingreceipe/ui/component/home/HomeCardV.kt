package com.berakahnd.cookingreceipe.ui.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun HomeCardV(
    modifier : Modifier = Modifier,
    item : CookingModelItem,
    onFavoritemClick : (FavCookingReceipe) -> Unit,
    goToDetailScreenClick : (CookingModelItem) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp * 60 / 100
    val width = (screenWidth / 2)

    val fav = FavCookingReceipe(continent = item.continent, cookingSteps = item.cookingSteps,
        cookingTime = item.cookingTime, imageUrl = item.imageUrl, ingredients = item.ingredients,
        receipeName = item.receipeName
    )
    ElevatedCard(
        modifier = modifier.width(screenWidth),
        onClick = { goToDetailScreenClick(item) }) {
        Column(modifier = modifier
            .fillMaxWidth()
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(
                modifier = modifier.align(alignment = Alignment.CenterHorizontally).size(width)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant, shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ){
                AsyncImage(modifier = modifier.padding(16.dp).size(width).clip(CircleShape),
                    alignment = Alignment.Center,
                    contentScale  = ContentScale.Crop, model = item.imageUrl, contentDescription = null)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(text = "${item.receipeName}",fontSize = 18.sp,fontWeight = FontWeight.Bold,overflow = TextOverflow.Clip,
                    softWrap = true,maxLines = 1)
                Text(text = "${item.continent}",overflow = TextOverflow.Clip,
                    softWrap = true,maxLines = 1,color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Row(
                modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${item.cookingTime}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                IconButton(onClick = {
                    onFavoritemClick(fav)
                }) {
                    Icon(imageVector = Icons.Default.BookmarkAdd, contentDescription = null)
                }
            }
        }
    }
}

