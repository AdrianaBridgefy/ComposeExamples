package com.example.composableexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.example.composableexample.ui.theme.ComposableExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableExampleTheme {
                // A surface container using the 'background' color from the theme
                ItemScreen()
            }
        }
    }
}

@Composable
fun ItemScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Magenta)
                .height(300.dp)
                .weight(3f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.TopStart)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.TopEnd)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.BottomStart)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.BottomEnd)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "0",
            onValueChange = {},
            modifier = Modifier.padding(4.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.weight(1f)) {
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
            ) {
                Text(text = "Increase")
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
            ) {
                Text(text = "Decrease")
            }
        }
    }
}

@Preview
@Composable
fun PreviewItemScreen() {
    ItemScreen()
}

@Composable
fun ConstraintItemScreen() {
    val constraints = ConstraintSet {
        val box = createRefFor("box")
        constrain(box) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }

        val input = createRefFor("input")
        val increase = createRefFor("increase")
        val decrease = createRefFor("decrease")
        constrain(input) {
            top.linkTo(box.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(increase) {
            top.linkTo(input.bottom)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            end.linkTo(decrease.start)
            width = Dimension.fillToConstraints
        }
        constrain(decrease) {
            top.linkTo(input.bottom)
            start.linkTo(increase.end)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
        }
    }
    ConstraintLayout(modifier = Modifier.fillMaxHeight(), constraintSet = constraints) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Magenta)
                .height(500.dp)
                .layoutId("box"),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Blue)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.TopStart)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.TopEnd)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.BottomStart)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.BottomEnd)
            )
        }

        OutlinedTextField(
            value = "0",
            onValueChange = {},
            modifier = Modifier.padding(4.dp)
                .layoutId("input")
        )

        Button(
            onClick = {},
            modifier = Modifier
                .padding(4.dp)
                .layoutId("increase")
        ) {
            Text(text = "Increase")
        }
        Button(
            onClick = {},
            modifier = Modifier
                .padding(4.dp)
                .layoutId("decrease")
        ) {
            Text(text = "Decrease")
        }
    }
}

@Preview
@Composable
fun PreviewConstraintItemScreen() {
    ConstraintItemScreen()
}

/* @Composable
fun MyPictureComposable(image: Int, description: String, modifier: Modifier) {
    Image(
        painter = painterResource(id = image),
        contentDescription = description,
        modifier = modifier
    )
}

@Preview
@Composable
fun MyPreviewComposable() {
    MyPictureComposable(
        image = R.drawable.my_pug,
        description = "Pug",
        modifier = Modifier
            .background(Color.White)
            .border(5.dp, Color.Blue)
            .padding(16.dp)
            .border(5.dp, Color.Cyan, shape = RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
    )
}*/
