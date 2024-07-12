package com.berakahnd.cookingreceipe.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.berakahnd.cookingreceipe.R

@Composable
fun SplashScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.background))
    val progress by animateLottieCompositionAsState(composition,iterations = LottieConstants.IterateForever)

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        LottieAnimation(
            modifier = modifier.fillMaxSize(),
            composition = composition,
            progress = { progress },
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier.padding(horizontal = 40.dp).padding(bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Cooking", fontSize = 45.sp, fontWeight = FontWeight.ExtraBold,
                color = Color.White)
            Text("Le lorem ipsum est, en imprimerie, une suite de mots sans signification utilisée " +
                    "à titre provisoire pour calibrer une mise en page",
                color = Color.White)
            Button(modifier = modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate( NavigationItem.Home.route)
                }
            ) {
                Text("Start")
            }
        }

    }

}
@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    //SplashScreen()
}