package com.example.composableexample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class StateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // val (count, onCountChange) = remember { mutableStateOf(0) }
            val countVM: CounterViewModel = viewModel()
            val count by countVM.count.observeAsState(0)
            BoxScreen(count) { newCount -> countVM.onCountChanged(newCount) }
        }
    }
}

@Composable
fun BoxScreen(count: Int, onCountChange: (Int) -> Unit) {
    val boxSize = 400.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(boxSize)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            var childSize = boxSize - 20.dp
            for (i in 0 until count) {
                Box(
                    modifier = Modifier
                        .size(childSize)
                        .rotate(i * 3f)
                        .background(Color.Gray)
                        .border(1.dp, Color.Black)
                )
                childSize -= 20.dp
            }
        }

        OutlinedTextField(value = "$count", onValueChange = {})
        Row {
            Button(onClick = { onCountChange(count + 1) }, modifier = Modifier.padding(8.dp)) {
                Text(text = "Increase")
            }

            Button(
                onClick = { onCountChange(if (count - 1 <= 0) 0 else count - 1) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Decrease")
            }
        }
    }
}

@Preview
@Composable
fun PreviewBoxScreen() {
    var count by remember { mutableStateOf(0) }
    BoxScreen(count) { newCount -> count = newCount }
}
