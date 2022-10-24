package com.example.composableexample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AnimatedContentCrossfadeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AnimatedContentUi() }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentUi() {
    var count by remember {
        mutableStateOf(0)
    }

    var expand by remember {
        mutableStateOf(false)
    }

    var pick by remember {
        mutableStateOf(Pictures.Kirby)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .shadow(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .padding(8.dp)
        ) {
            Text(
                text = "Animated Content: Sample 1",
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h6
            )
            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(8.dp)
                    .background(Color.Yellow),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { if (count > 0) count-- },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "decrease",
                        modifier = Modifier.size(100.dp)
                    )
                }

                AnimatedContent(
                    targetState = count,
                    transitionSpec = {
                        if (targetState > initialState) {
                            slideInHorizontally { width -> -width } + fadeIn() with slideOutHorizontally { width -> width } + fadeOut() using
                                SizeTransform { initialSize, targetSize ->
                                    keyframes {
                                        IntSize(
                                            initialSize.width / 2,
                                            targetSize.height / 2
                                        )
                                    }
                                }
                        } else {
                            slideInHorizontally { width -> -width } + fadeIn() with slideOutHorizontally { width ->
                                -width
                            } + fadeOut()
                        }
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .width(100.dp)
                            .padding(5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$count",
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                IconButton(
                    onClick = { count++ },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "increase",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Animated Content: Sample 2",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Expand",
                    modifier = Modifier.padding(8.dp)
                )
                Checkbox(checked = expand, onCheckedChange = { expand = !expand })
            }

            Divider()
            AnimatedContent(
                targetState = expand,
                transitionSpec = {
                    slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Up,
                        animationSpec =
                        spring(Spring.DampingRatioHighBouncy)
                    ) + fadeIn() with slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Up
                    ) + fadeOut() using
                        SizeTransform { initialSize, targetSize ->
                            keyframes {
                                durationMillis = 300
                                IntSize(initialSize.width / 2, targetSize.height / 2) at 150
                            }
                        }
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ) {
                    if (!expand) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                        ) {
                            Text(text = "short Text", modifier = Modifier.padding(8.dp))
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Long text\nLong text line 2\nLong text line 3",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Animated Crossfade",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(16.dp)
            )

            Divider()

            val targetState = pick
            Crossfade(
                targetState = pick,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = LinearEasing
                )
            ) { picture ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            val items = Pictures.values()
                            val nextItem =
                                if (picture.ordinal < items.size - 1) items[picture.ordinal + 1]
                                else items[0]
                            pick = nextItem
                        }
                ) {
                    Text(
                        text = targetState.name,
                        modifier = Modifier
                            .padding(8.dp),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    when (targetState) {
                        Pictures.Kirby -> {
                            Image(
                                painter = painterResource(id = R.drawable.kirby),
                                contentDescription = "Kirby",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Pictures.Link -> {
                            Image(
                                painter = painterResource(id = R.drawable.link),
                                contentDescription = "Link",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .clip(CircleShape)

                            )
                        }
                        Pictures.Zelda -> {
                            Image(
                                painter = painterResource(id = R.drawable.zelda),
                                contentDescription = "Zelda",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Pictures.Pikachu -> {
                            Image(
                                painter = painterResource(id = R.drawable.pikachu),
                                contentDescription = "Pikachu",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .clip(CircleShape)
                            )
                        }
                        Pictures.All -> {
                            Row(Modifier.fillMaxSize()) {
                                val pics = listOf(
                                    R.drawable.kirby,
                                    R.drawable.link,
                                    R.drawable.zelda,
                                    R.drawable.pikachu
                                )
                                for (pic in pics)
                                    Image(
                                        painterResource(id = pic),
                                        null,
                                        Modifier
                                            .weight(1f)
                                            .align(Alignment.Bottom)
                                            .graphicsLayer {
                                                if (pic == R.drawable.kirby) rotationY = 180f
                                            }
                                    )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimations() {
    AnimatedContentUi()
}
