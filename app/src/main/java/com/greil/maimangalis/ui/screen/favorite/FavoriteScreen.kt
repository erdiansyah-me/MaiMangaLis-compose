package com.greil.maimangalis.ui.screen.favorite

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greil.maimangalis.R
import com.greil.maimangalis.domain.model.MangaListModel
import com.greil.maimangalis.ui.component.MangaItem
import com.greil.maimangalis.ui.viewmodel.MangaViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: MangaViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    backButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favorite",
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
                        modifier = Modifier.padding(start = 12.dp).clickable { backButtonClick() }
                    )
                },
                backgroundColor = Color.White
            )
        }
    ) {
        viewModel.favoriteManga.collectAsState(initial = emptyList()).value.let { state ->
            if (state.isNotEmpty()) {
                FavoriteContent(modifier = modifier.padding(it), navigateToDetail, data = state)
            } else {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = stringResource(R.string.empty_favorite))
                    }
                }
            }
        }
    }

}

@Composable
fun FavoriteContent(
    modifier: Modifier,
    navigateToDetail: (Int) -> Unit,
    data: List<MangaListModel>,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(172.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
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