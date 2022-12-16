package com.greil.maimangalis.ui.screen.detail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.greil.maimangalis.R
import com.greil.maimangalis.data.State
import com.greil.maimangalis.domain.model.MangaDetailModel
import com.greil.maimangalis.domain.model.MangaListModel
import com.greil.maimangalis.ui.theme.MaiMangaLisTheme
import com.greil.maimangalis.ui.viewmodel.MangaViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.properties.Delegates

@Composable
fun DetailScreen(
    mangaId: Int,
    navigateUp: () -> Unit,
    viewModel: MangaViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                            text = "Detail Manga",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp
                        )
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        modifier = Modifier
                            .padding(end = 8.dp, start = 12.dp)
                            .clickable { navigateUp() }
                    )
                },
                backgroundColor = Color.White
            )
        },
    ) { padding ->
        if (mangaId == 0) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(text = stringResource(R.string.error_page))
            }
        } else {
            viewModel.setIdSelect(mangaId)
            viewModel.setIsFavorite(mangaId)
            viewModel.mangaDetail.collectAsState(initial = State.Loading()).value.let { state ->
                when(state) {
                    is State.Loading -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    is State.Success -> {
                        DetailContent(modifier = Modifier.padding(12.dp), dataDetailModel = state.data, viewModel = viewModel)
                    }
                    is State.Error -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            if (state.message != null) {
                                Text(text = state.message)
                            }
                        }
                    }
                    else -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Text(text = "Something Wrong!")
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun DetailContent(
    modifier: Modifier,
    dataDetailModel: MangaDetailModel?,
    viewModel: MangaViewModel,
) {
    var isFavorite by Delegates.notNull<Boolean>()
    val isFav by viewModel.isFavoriteManga.collectAsState(initial = false)
    var iconDis by remember {
        mutableStateOf(isFav)
    }
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = dataDetailModel?.imageUrl ?: "",
                contentDescription = null,
                modifier = Modifier
                    .width(128.dp)
                    .aspectRatio(3 / 4F)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    text = dataDetailModel?.title ?: "-",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Column() {
                    dataDetailModel?.author?.map {
                        Text(
                            text = it?.name ?: "-",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
                Text(
                    text = stringResource(R.string.manga_status, dataDetailModel?.status ?: "-"),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.LightGray)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    tint = Color(0xFFFFA500),
                    contentDescription = null
                )
                Text(
                    text = dataDetailModel?.score.toString(),
                    modifier = Modifier.padding(top = 12.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        isFavorite = !isFavorite
                        if (isFavorite) {
                            viewModel.insertFavoriteManga(
                                MangaListModel(
                                    id = dataDetailModel?.id,
                                    title = dataDetailModel?.title,
                                    imageUrl = dataDetailModel?.imageUrl
                                )
                            )
                            Toast.makeText(context,"Added to Favorite Manga", Toast.LENGTH_SHORT).show()
                        } else {
                            if (dataDetailModel?.id != null) {
                                viewModel.deleteNonFavoriteManga(dataDetailModel.id)
                                Toast.makeText(context,"Removed from Favorite Manga", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context,"Manga Not Found", Toast.LENGTH_SHORT).show()
                            }
                        }
                        viewModel.setIsFavorite(dataDetailModel?.id!!)
                        iconDis = !iconDis
                    }
                ) {
                    viewModel.isFavoriteManga.collectAsState(initial = false).value.let {
                        isFavorite = if (it != iconDis) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = stringResource(R.string.manga_favorite),
                                tint = Color.Red.copy(alpha = 0.5F)
                            )
                            true
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                contentDescription = stringResource(R.string.manga_favorite),
                                tint = Color.Gray
                            )
                            false
                        }

                    }
                }
                Text(
                    text = stringResource(R.string.manga_favorite),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.LightGray)
                .padding(top = 4.dp)
        )
        Text(
            text = "Synopsis :",
            modifier = modifier.padding(top = 8.dp, bottom = 8.dp, start = 8.dp),
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(text = dataDetailModel?.synopsis ?: "-", modifier = Modifier.padding(bottom = 8.dp))
            Text(text = dataDetailModel?.background ?: "-")
        }
    }
}