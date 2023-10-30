package ru.den.omg.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import ru.den.omg.R
import ru.den.omg.navigations.Screens
import ru.den.omg.ui.theme.Purple80

@Composable
fun AnimatedSplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alfaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        ), label = "Денис"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(10_00)
        navController.navigate(Screens.Home.route)
    }
    Splash(alfaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box (
       modifier = Modifier
           .background(if (isSystemInDarkTheme()) Color.Black else Purple80)
           .alpha(alpha),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.Build,
            contentDescription = "Ден",
            tint = Color.White,
            modifier = Modifier.size(120.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.den),
            contentDescription = "Ден",
            modifier = Modifier.size(220.dp)
        )
    }
}