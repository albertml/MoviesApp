package com.example.moviesapp.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.moviesapp.android.screens.details.MovieDetailsScreen
import com.example.moviesapp.android.screens.search.MovieSearchScreen
import com.example.moviesapp.ui.viewmodels.MovieDetailsViewModel
import com.example.moviesapp.ui.viewmodels.MoviesViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

sealed class Dest {

    @Serializable
    data object Search: Dest()
    @Serializable
    data class Details(val imdbId: String): Dest()
}


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Dest.Search) {

        composable<Dest.Search> {
            val viewModel = koinViewModel<MoviesViewModel>()
            MovieSearchScreen(viewModel = viewModel) {
                navController.navigate(Dest.Details(it))
            }
        }

        composable<Dest.Details> {
            val imdbId = it.toRoute<Dest.Details>().imdbId
            val viewModel: MovieDetailsViewModel = koinViewModel()

            LaunchedEffect(
                key1 = imdbId
            ) {
                viewModel.getMovieDetails(imdbId)
            }

            MovieDetailsScreen(viewModel = viewModel)
        }
    }
}