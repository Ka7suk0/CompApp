package org.ka7suk0sail.compapp.uiScreens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import compapp.composeapp.generated.resources.Res
import compapp.composeapp.generated.resources.chat_notifications
import compapp.composeapp.generated.resources.go_back
import compapp.composeapp.generated.resources.my_tasks
import org.jetbrains.compose.resources.stringResource
import org.ka7suk0sail.compapp.Models.Data.Favor
import org.ka7suk0sail.compapp.ViewModels.FavorDetailViewModel
import org.ka7suk0sail.compapp.uiCommon.LoadingIndicator
import org.ka7suk0sail.compapp.uiScreens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavorDetailsScreen(vm: FavorDetailViewModel, onBack: () -> Unit){
    val state = vm.state
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior ()
    Screen {
        Scaffold(
            topBar = {
                DetailTopBar(
                    title = state.favor?.name ?:"",
                    onBack = onBack,
                    scrollBehavior = scrollBehavior
                )
            }
        ){ padding ->
            LoadingIndicator(
                enabled = state.loading,
                modifier = Modifier.padding(padding)
            )

            state.favor?.let{ favor ->
                FavorDetail(
                    favor = favor,
                    modifier = Modifier.padding(padding)
                )
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopBar(
    title: String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(Res.string.go_back)
                )
            }
        },
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton(onClick = {/*TODO my tasks*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = stringResource(Res.string.my_tasks)
                )
            }
            IconButton(onClick = { /*TODO notification*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = stringResource(Res.string.chat_notifications)
                )
            }
        }
    )
}

@Composable
private fun FavorDetail(
    favor: Favor,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = favor.authorImage,
            contentDescription = favor.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )
        Text(
            text = favor.name,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}