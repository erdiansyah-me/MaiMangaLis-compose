package com.greil.maimangalis

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.greil.maimangalis.ui.navigation.Screen
import com.greil.maimangalis.ui.screen.detail.DetailScreen
import com.greil.maimangalis.ui.screen.favorite.FavoriteScreen
import com.greil.maimangalis.ui.screen.home.HomeScreen
import com.greil.maimangalis.ui.theme.MaiMangaLisTheme
import com.greil.maimangalis.ui.viewmodel.MangaViewModel
import com.greil.mangaingfo.ui.screen.about.AboutScreen

@Composable
fun MaiMangaLisApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToDetail = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                },
                navigateToAbout = {
                    navController.navigate(Screen.About.route)
                },
                navigateToFavorite = {
                    navController.navigate(Screen.Favorite.route)
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            DetailScreen(
                mangaId = id,
                navigateUp = {navController.navigateUp()}
            )
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen(
                backButtonClick = {
                    navController.navigateUp()
                },
                navigateToDetail = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                },
            )
        }
        composable(Screen.About.route) {
            AboutScreen(
                backButtonClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}