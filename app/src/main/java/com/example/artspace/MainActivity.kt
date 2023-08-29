package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(
    modifier: Modifier = Modifier
) {
    var artNumber by remember { mutableStateOf(1) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .weight(1f)
        ) {
            ArtworkWall(
                artworkId = artworkResource(artNumber),
                modifier = Modifier
                    .padding(20.dp)
            )
            ArtworkDescription(
                titleStringId = titleResource(artNumber),
                authorStringId = R.string.author
            )
        }
        DisplayController(
            onPreviousClick = { artNumber = previous(artNumber) },
            onNextClick = { artNumber = next(artNumber) }
        )
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes artworkId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 4.dp,
        border = BorderStroke(24.dp, Color.White),
        modifier = modifier
            .wrapContentSize()
    ) {
        Image(
            painter = painterResource(artworkId),
            contentScale = ContentScale.Inside,
            contentDescription = null,
            modifier = Modifier
                .padding(24.dp)
        )
    }
}

@Composable
fun ArtworkDescription(
    @StringRes titleStringId: Int,
    @StringRes authorStringId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 4.dp,
        modifier = modifier
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = stringResource(id = titleStringId),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(id = authorStringId),
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier
                .width(150.dp)
        ) {
            Text(
                text = stringResource(R.string.previous)
            )
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier
                .width(150.dp)
        ) {
            Text(
                text = stringResource(R.string.next)
            )
        }
    }
}

fun artworkResource(artNumber: Int): Int {
    return when (artNumber) {
        1 -> R.drawable.artwork_1
        2 -> R.drawable.artwork_2
        3 -> R.drawable.artwork_3
        4 -> R.drawable.artwork_4
        5 -> R.drawable.artwork_5
        6 -> R.drawable.artwork_6
        7 -> R.drawable.artwork_7
        8 -> R.drawable.artwork_8
        9 -> R.drawable.artwork_9
        10 -> R.drawable.artwork_10
        11 -> R.drawable.artwork_11
        12 -> R.drawable.artwork_12
        13 -> R.drawable.artwork_13
        else -> R.drawable.artwork_14
    }
}

fun titleResource(artNumber: Int): Int {
    return when (artNumber) {
        1 -> R.string.artwork_title_1
        2 -> R.string.artwork_title_2
        3 -> R.string.artwork_title_3
        4 -> R.string.artwork_title_4
        5 -> R.string.artwork_title_5
        6 -> R.string.artwork_title_6
        7 -> R.string.artwork_title_7
        8 -> R.string.artwork_title_8
        9 -> R.string.artwork_title_9
        10 -> R.string.artwork_title_10
        11 -> R.string.artwork_title_11
        12 -> R.string.artwork_title_12
        13 -> R.string.artwork_title_13
        else -> R.string.artwork_title_14
    }
}

fun previous(artNumber: Int): Int {
    var newImageNumber = artNumber
    newImageNumber--
    return if (newImageNumber in 1..14) {
        newImageNumber
    } else {
        14
    }
}

fun next(artNumber: Int): Int {
    var newImageNumber = artNumber
    newImageNumber++
    return if (newImageNumber in 1..14) {
        newImageNumber
    } else {
        1
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}