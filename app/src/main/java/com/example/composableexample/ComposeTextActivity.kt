package com.example.composableexample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ComposeTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTextInput()
        }
    }
}

@Composable
fun TextUi(text: String) {
    Column {
        val customFont = Font(R.font.sacred_valley)
        val customFonFamily = FontFamily(customFont)
        // basic
        BasicText(
            text = "Welcome",
            style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        BasicText(
            text = text,
            modifier = Modifier.padding(4.dp),
            style = TextStyle.Default.copy(
                color = Color.Red,
                fontSize = 50.sp,
                fontFamily = customFonFamily
            )
        )

        // text composable
        Text(
            text = text,
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.h4.copy(
                color = Color.Green,
                fontSize = 60.sp,
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextUi() {
    val textToDisplay = "Welcome to Jetpack Compose"
    TextUi(textToDisplay)
}

@Composable
fun MyTextInput() {
    Column {
        Text("Basic text field")
        var basicText by remember {
            mutableStateOf("")
        }
        BasicTextField(
            value = basicText,
            onValueChange = { basicText = it },
            modifier = Modifier.padding(8.dp)
        )

        Text("Text field")
        var tfText by remember {
            mutableStateOf("")
        }
        TextField(value = tfText, onValueChange = { tfText = it })

        var otfText by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = otfText,
            onValueChange = { otfText = it },
            label = { Text("Outlined") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }
}
