package com.greil.maimangalis.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greil.maimangalis.data.State
import com.greil.maimangalis.domain.model.MangaListModel
import com.greil.maimangalis.ui.component.MangaItem
import com.greil.maimangalis.ui.viewmodel.MangaViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MangaViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateToAbout: () -> Unit,
    navigateToFavorite: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp
                        )
                    )
                },
                actions = {

                    IconButton(onClick = {
                        navigateToAbout()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null,
                            modifier = modifier.padding(end = 4.dp)
                        )
                    }
                    IconButton(onClick = {
                        navigateToFavorite()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            modifier = modifier.padding(end = 8.dp)
                        )
                    }
                },
                backgroundColor = Color.White
            )
        }
    ) {
        viewModel.mangaList.collectAsState(initial = State.Loading()).value.let { state ->
            when(state) {
                is State.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                is State.Success -> {
                    state.data?.let { it1 ->
                        HomeContent(modifier = modifier.padding(it), navigateToDetail,
                            it1
                        )
                    }
                }
                is State.Error -> {}
                else -> {}
            }
        }
    }

}

@Composable
fun HomeContent(
    modifier: Modifier,
    navigateToDetail: (Int) -> Unit,
    data: List<MangaListModel>,
) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(172.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.fillMaxSize().padding(12.dp)
        ) {
            items(data) { manga ->
                MangaItem(
                    imageUrl = manga.imageUrl,
                    title = manga.title.orEmpty(),
                    modifier = Modifier.clickable {navigateToDetail(manga.id ?: 0)}
                )
            }
        }
}