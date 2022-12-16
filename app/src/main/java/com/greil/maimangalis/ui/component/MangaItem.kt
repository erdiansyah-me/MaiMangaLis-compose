package com.greil.maimangalis.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.greil.maimangalis.R
import com.greil.maimangalis.ui.theme.MaiMangaLisTheme

@Composable
fun MangaItem(
    imageUrl: String?,
    title: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.width(192.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.padding(4.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = stringResource(id = R.string.manga_cover),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(192.dp)
                    .aspectRatio(3 / 4F),
                error = painterResource(id = R.drawable.image_error),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                    color = Color.Black.copy(
                        alpha = 0.5F
                    )
                )
            ) {
                Text(
                    text = title,
                    modifier = Modifier.padding(4.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                    )
                )
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun MangaItemPrev() {
    MaiMangaLisTheme {
        MangaItem(imageUrl = "", title = "Berserk")
    }
}