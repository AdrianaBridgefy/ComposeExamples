package com.example.composableexample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composableexample.ui.theme.ComposableExampleTheme

class AnimatedVisibilityMotionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableExampleTheme {
                AnimatedVisibilitySample()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilitySample() {
    val (checked, onCheckChange) = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .shadow(2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Checkbox(checked = checked, onCheckedChange = onCheckChange)
            Text("Show Content", modifier = Modifier.padding(10.dp))
        }
        AnimatedVisibility(
            visible = checked,
            enter = fadeIn() +
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        visibilityThreshold = IntOffset(100, 100)
                    )
                ),
            exit = shrinkOut(
                shrinkTowards = Alignment.BottomStart,
                animationSpec = tween(1000)
            )
        ) {
            Box(
                modifier = Modifier.size(400.dp).padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.my_pug),
                    contentDescription = "Icon test",
                    modifier = Modifier.clip(RoundedCornerShape(8.dp)).fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Jetpack Compose is awesome",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = { 2 * it },
                                animationSpec = tween(durationMillis = 1000)
                            )
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimation() {
    AnimatedVisibilitySample()
}
