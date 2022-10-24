package com.example.composableexample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NetworkImage() }
    }
}

@Composable
fun ComposeImages() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Vector Graphic")
        Image(
            painter = painterResource(id = R.drawable.ic_ac),
            contentDescription = "Android(TM)",
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Bitmap Graphic")
        Spacer(modifier = Modifier.height(65.dp))
        Image(
            painter = painterResource(id = R.drawable.my_pug),
            contentDescription = "pug",

            modifier = Modifier.fillMaxWidth().height(380.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

// @Preview(showBackground = true)
@Composable
fun PreviewImages() {
    ComposeImages()
}

@Composable
fun NetworkImage() {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://picsum.photos/400/400")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.my_pug),
            contentDescription = "description",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNetworkImage() {
    NetworkImage()
}
