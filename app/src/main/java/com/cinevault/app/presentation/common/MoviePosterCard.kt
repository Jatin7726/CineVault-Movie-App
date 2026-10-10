package com.cinevault.app.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.cinevault.app.domain.model.Movie
import com.cinevault.app.presentation.theme.CineVaultTheme

/**
 * Reused on Home (trending row + popular grid), Search and Watchlist -
 * one source of truth for "what a movie card looks like". Width is
 * entirely caller-controlled via `modifier` (e.g. Modifier.width(118.dp)
 * in a LazyRow, or Modifier.fillMaxWidth() inside a grid cell) so this
 * composable never fights its parent's layout.
 */
@Composable
fun MoviePosterCard(
    movie: Movie,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(10.dp))
                .clickable { onClick(movie.id) },
        )
        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth().padding(top = 18.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviePosterCardPreview() {
    CineVaultTheme {
        MoviePosterCard(
            movie = Movie(
                id = 1,
                title = "Inception",
                overview = "A thief who steals corporate secrets...",
                posterPath = null,
                backdropPath = null,
                releaseDate = "2010-07-16",
                voteAverage = 8.8,
            ),
            onClick = {},
        )
//        MoviePosterCard(
//            movie = Movie(
//                id = 1,
//                title = "Interstellar",
//            ),
//            onClick = {},
//            modifier = Modifier
//                .width(160.dp)
//                .padding(8.dp),
//        )
    }
}
