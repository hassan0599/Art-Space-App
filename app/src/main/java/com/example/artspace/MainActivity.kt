package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var imageNumber by remember { mutableStateOf(1) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 25.dp)
                .fillMaxSize()
        ) {
            Artwork(modifier = Modifier.weight(1f), imageNumber)
            Description(imageNumber)
        }
        ButtonRow(
            previousFunction = { imageNumber = previousImage(imageNumber) } ,
            nextFunction = { imageNumber = nextImage(imageNumber) })
    }
}

@Composable
fun Description(imageNumber: Int) {
    Surface(
        elevation = 10.dp,
        modifier = Modifier
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Text(
                text = stringResource(descriptionResource(imageNumber)),
                fontWeight = FontWeight.Light,
                fontSize = 30.sp
            )
            Text(
                text = stringResource(photographerResource(imageNumber)),
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun Artwork(modifier: Modifier = Modifier, imageNumber: Int) {
    Surface(
        modifier = modifier
            .wrapContentSize()
            .padding(
                20.dp
            )
    ) {
        Surface(
            modifier = Modifier
                .border(
                    BorderStroke(5.dp, Color(0xFF888888))
                ),
            elevation = 10.dp
        ) {
            Image(
                painter = painterResource(drawableResource(imageNumber)),
                contentDescription = stringResource(descriptionResource(imageNumber)),
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .padding(50.dp)
            )
        }
    }
}


@Composable
fun ButtonRow(previousFunction: () -> Unit, nextFunction: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        MyButton(
            R.string.previous_button,
            onClick = previousFunction
        )
        MyButton(
            R.string.next_button,
            onClick = nextFunction
        )
    }
}

@Composable
fun MyButton(@StringRes stringResource: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(150.dp)
    ) {
        Text(
            text = stringResource(stringResource)
        )
    }
}

fun drawableResource(imageNumber: Int):  Int {
    return when (imageNumber) {
        1 -> R.drawable.art_work_1
        2 -> R.drawable.art_work_2
        3 -> R.drawable.art_work_3
        4 -> R.drawable.art_work_4
        5 -> R.drawable.art_work_5
        6 -> R.drawable.art_work_6
        7 -> R.drawable.art_work_7
        else -> R.drawable.art_work_8

    }
}

fun descriptionResource(imageNumber: Int): Int {
    return when (imageNumber) {
        1 -> R.string.description_1
        2 -> R.string.description_2
        3 -> R.string.description_3
        4 -> R.string.description_4
        5 -> R.string.description_5
        6 -> R.string.description_6
        7 -> R.string.description_7
        else -> R.string.description_8
    }
}

fun photographerResource(imageNumber: Int): Int {
    return when (imageNumber) {
        1 -> R.string.photographer_1
        2 -> R.string.photographer_2
        3 -> R.string.photographer_3
        4 -> R.string.photographer_4
        5 -> R.string.photographer_5
        6 -> R.string.photographer_6
        7 -> R.string.photographer_7
        else -> R.string.photographer_8

    }
}

fun nextImage(imageState: Int): Int {
    var newImageState = imageState
    newImageState++
    return if (newImageState in 1..8) {
        newImageState
    } else {
        1
    }
}

fun previousImage(imageState: Int): Int {
    var newImageState = imageState
    newImageState--
    return if (newImageState in 1..8) {
        newImageState
    } else {
        1
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}