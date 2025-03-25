package org.ka7suk0sail.compapp

import androidx.compose.runtime.*
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.ka7suk0sail.compapp.uiScreens.Navigation.Navigation
/*
import org.ka7suk0sail.compapp.uiScreens.Navigation.MainNavigation
import org.ka7suk0sail.compapp.uiScreens.Navigation.rememberEducationsRepository
import org.ka7suk0sail.compapp.uiScreens.Navigation.rememberFavorsRepository
import org.ka7suk0sail.compapp.uiScreens.Navigation.rememberRidesRepository
*/

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
        .crossfade(true)
        .logger(DebugLogger())
        .build()
    }
    /*
    val favorsRepository = rememberFavorsRepository()
    val educationsRepository = rememberEducationsRepository()
    val ridesRepository = rememberRidesRepository()

    MainNavigation(
        favorsRepository = favorsRepository,
        educationsRepository = educationsRepository,
        ridesRepository = ridesRepository
    )

    */


    Navigation()

}
