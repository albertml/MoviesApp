package com.example.moviesapp.android.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviesapp.ui.viewmodels.MoviesViewModel

@Composable
fun MovieSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel,
    onClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var query by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TextField(
                value = query,
                onValueChange = {
                    query = it
                    viewModel.updateQuery(query)
                }, modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ), placeholder = { Text(text = "Search...") }
            )
        }) {
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center) {

                CircularProgressIndicator()
            }
        }

        if (uiState.error.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center) {

                Text(text = uiState.error)
            }
        }

        uiState.movies?.let { response ->
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                items(response) {
                    Column(
                        modifier = Modifier
                            .clickable {
                                onClick.invoke(it.imdbID )
                            }
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        com.skydoves.landscapist.glide.GlideImage(
                            imageModel = it.poster,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            }

        }
    }
}