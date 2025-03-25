package org.ka7suk0sail.compapp.uiScreens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.ka7suk0sail.compapp.Models.Data.Education
import org.ka7suk0sail.compapp.Models.Data.Favor
import org.ka7suk0sail.compapp.Models.Data.Ride
import org.ka7suk0sail.compapp.ViewModels.EducationDetailViewModel
import org.ka7suk0sail.compapp.ViewModels.FavorDetailViewModel
import org.ka7suk0sail.compapp.ViewModels.RideDetailViewModel
import org.ka7suk0sail.compapp.uiCommon.LoadingIndicator

@Composable
fun FavorDetailsDialog(vm: FavorDetailViewModel, onDismiss: () -> Unit) {
    val state = vm.state
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(16.dp)
        ) {
            if (state.loading) {
                LoadingIndicator(enabled = state.loading)
            } else {
                state.favor?.let { favor ->
                    FavorDetailContent(favor = favor)
                }
            }
        }
    }
}

@Composable
private fun FavorDetailContent(
    favor: Favor,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
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
        // Add more details as needed
    }
}

@Composable
fun EducationDetailsDialog(vm: EducationDetailViewModel, onDismiss: () -> Unit) {
    val state = vm.state
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(16.dp)
        ) {
            if (state.loading) {
                LoadingIndicator(enabled = state.loading)
            } else {
                state.education?.let { education ->
                    EducationDetailContent(education = education)
                }
            }
        }
    }
}

@Composable
private fun EducationDetailContent(
    education: Education,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AsyncImage(
            model = education.authorImage,
            contentDescription = education.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )
        Text(
            text = education.name,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        // Add more details as needed
    }
}

@Composable
fun RideDetailsDialog(vm: RideDetailViewModel, onDismiss: () -> Unit) {
    val state = vm.state
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.padding(16.dp)
        ) {
            if (state.loading) {
                LoadingIndicator(enabled = state.loading)
            } else {
                state.ride?.let { ride ->
                    RideDetailContent(ride = ride)
                }
            }
        }
    }
}

@Composable
private fun RideDetailContent(
    ride: Ride,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ride.authorImage,
            contentDescription = ride.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )
        Text(
            text = ride.name,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        // Add more details as needed
    }
}
