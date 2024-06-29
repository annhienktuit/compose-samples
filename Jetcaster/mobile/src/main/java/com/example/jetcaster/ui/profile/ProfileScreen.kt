package com.example.jetcaster.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.window.core.layout.WindowSizeClass
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.jetcaster.core.model.ProfileInfo

@Composable
fun ProfileScreen(
    windowSizeClass: WindowSizeClass,
    onBackPress: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val profileInfo = ProfileInfo(
        "1",
        "Maguire",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZdq8BpP8g-6ArJZxW8OqyLzWfeaIJIK-5Lw&s"
    )
    var imagePainterState by remember {
        mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty)
    }
    val imageLoader = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(profileInfo.avatarUrl)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        onState = { state -> imagePainterState = state }
    )
    Column(modifier = modifier) {
        Image(
            painter = imageLoader,
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = modifier.size(168.dp).clip(MaterialTheme.shapes.medium),
        )
    }
}

@Composable
fun ProfileHeader(
    profileInfo: ProfileInfo,
    modifier: Modifier = Modifier.padding(16.dp)
) {
    var imagePainterState by remember {
        mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty)
    }
    val imageLoader = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(profileInfo.avatarUrl)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        onState = { state -> imagePainterState = state }
    )
    Column(modifier = modifier) {
        Image(
            painter = imageLoader,
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = modifier,
        )
    }
}
