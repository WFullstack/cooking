package com.berakahnd.cookingreceipe.ui.component.home

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBodySkeleton(
) {
    val showShimmer = remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current
    val screenWidthH = configuration.screenWidthDp.dp * 80/ 100
    val screenWidthV = configuration.screenWidthDp.dp * 60 / 100

    Column(
        Modifier.padding(horizontal = 16.dp),
        //verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = "Cooking Recipe", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text(text = "become a chef with our recipes", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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
        value = "search", onValueChange = {}
    )
    Text(modifier = Modifier.padding(horizontal = 16.dp),text = "Category", fontSize = 18.sp)
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(5){
            Box(Modifier.background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value), shape = RoundedCornerShape(16.dp)).width(100.dp)
                .height((50.dp)),
                ) {
            }
        }
    }
    Text(modifier = Modifier.padding(horizontal = 16.dp),text = "List cooking recipe", fontSize = 18.sp)
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(horizontal = 16.dp)) {
        items(4){
            Box(
                modifier = Modifier
                    .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value), shape = RoundedCornerShape(16.dp))
                    .width(screenWidthV)
                    .height((screenWidthV + 50.dp))
            ) {
                //CircularProgressIndicator()
            }
        }
    }
    Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Popular Food", fontSize = 18.sp, )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(4){
            Box(
                modifier = Modifier
                    .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value), shape = RoundedCornerShape(16.dp))
                    .width(screenWidthH)
                    .height(100.dp)
            ) {
                //CircularProgressIndicator()
            }
        }
    }
}
@Composable
fun shimmerBrush(showShimmer: Boolean = true,targetValue:Float = 1000f): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )

        val transition = rememberInfiniteTransition()
        val translateAnimation = transition.animateFloat(
            initialValue = 0f, targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800), repeatMode = RepeatMode.Reverse
            )
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent,Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeBodySkeletonPreview(){
    Column(Modifier.fillMaxSize()){
        HomeBodySkeleton()
    }
}
