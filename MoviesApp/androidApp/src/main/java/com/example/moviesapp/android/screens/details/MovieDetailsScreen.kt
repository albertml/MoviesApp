package com.example.moviesapp.android.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviesapp.ui.viewmodels.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center) {

            CircularProgressIndicator()
        }
    }

    if (uiState.error.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center) {

            Text(text = uiState.error)
        }
    }

    uiState.movieDetails?.let { response ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            com.skydoves.landscapist.glide.GlideImage(
                imageModel = response.poster,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Title: " + response.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Plot: " + response.plot,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Language: " + response.language,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Genre: " + response.genre,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}