package com.berakahnd.cookingreceipe.ui.component.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.berakahnd.cookingreceipe.data.model.CookingModelItem

@Composable
fun DetailsBody(
    cookingModelItem : CookingModelItem
){
    val configuration = LocalConfiguration.current
    val screenImage = configuration.screenWidthDp.dp * 70 /100

    Column(modifier = Modifier) {
        Text(modifier = Modifier.padding(horizontal = 16.dp),text = cookingModelItem.receipeName, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(modifier = Modifier.fillMaxWidth().height(screenImage).clip(RoundedCornerShape(8.dp)),
            alignment = Alignment.Center, contentScale  = ContentScale.Crop, model = cookingModelItem.imageUrl,
            contentDescription = null)
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = cookingModelItem.cookingTime)
            Text(text = cookingModelItem.continent)
        }
        Spacer(modifier = Modifier.height(16.dp))
        cookingModelItem.ingredients.forEachIndexed { index, ingredient ->
            Text(modifier = Modifier.padding(horizontal = 16.dp), text = "- $ingredient" )
        }
        Spacer(modifier = Modifier.height(16.dp))
        cookingModelItem.cookingSteps.forEachIndexed { index, cookingStep ->
            Text(modifier = Modifier.padding(horizontal = 16.dp), text = "* $cookingStep" )
        }
    }
}

/*
@Composable
@Preview(showBackground = true)
fun DetailsBodyPreview(){
    val item = CookingModelItem(receipeName="Spaghetti Carbonara", continent="European",
        cookingSteps = listOf("Cook spaghetti in boiling salted water until al dente",
            "drain and reserve some pasta water."," In a pan, fry pancetta or guanciale until crispy. Remove from heat."
        , "In a bowl, whisk eggs with grated cheese, salt, and pepper.", "Combine cooked spaghetti with pancetta, then add egg mixture and toss quickly until creamy."
        , "Add reserved pasta water as needed for desired consistency.", "Serve immediately, garnished with chopped parsley if desired."),
        cookingTime="20 minutes", imageUrl="https://static01.nyt.com/images/2021/02/14/dining/carbonara-horizontal/carbonara-horizontal-articleLarge-v2.jpg",
        ingredients = listOf("200g spaghetti"," 100g pancetta or guanciale", "diced", "2 large eggs", "50g grated Pecorino Romano cheese", "Salt and black pepper to taste", "Fresh parsley (optional)"))
    DetailsBody(cookingModelItem =  item)
}
*/
