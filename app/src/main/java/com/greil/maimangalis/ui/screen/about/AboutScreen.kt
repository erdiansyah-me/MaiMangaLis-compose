package com.greil.mangaingfo.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.greil.maimangalis.R

@Composable
fun AboutScreen(
    backButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp
                        ),
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier.padding(start = 12.dp).clickable { backButtonClick() }
                    )
                },
                backgroundColor = Color.White
            )
        }
    ) {
        AboutContent()
    }
}

@Composable
fun AboutContent() {
       Column(
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.fillMaxSize(),
       ) {

           Image(
               painter = painterResource(id = R.drawable.foto_profil),
               contentDescription = stringResource(R.string.about_image),
               contentScale = ContentScale.Crop,
               modifier = Modifier
                   .padding(top = 64.dp, bottom = 12.dp)
                   .size(180.dp)
                   .clip(CircleShape)
           )
           Text(
               text = stringResource(R.string.about_name),
               style = MaterialTheme.typography.subtitle1.copy(
                   fontWeight = FontWeight.ExtraBold,
                   fontSize = 24.sp
               )
           )
           Text(
               text = stringResource(R.string.about_email),
               style = MaterialTheme.typography.subtitle2.copy(
                   fontWeight = FontWeight.SemiBold
               )
           )
       }
}

@Composable
@Preview(showBackground = true)
fun AboutScreenPreview() {
    AboutScreen() {

    }
}