package org.ka7suk0sail.compapp.uiScreens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import compapp.composeapp.generated.resources.Res
import compapp.composeapp.generated.resources.app_name
import compapp.composeapp.generated.resources.avatar
import compapp.composeapp.generated.resources.chat_notifications
import compapp.composeapp.generated.resources.configuration
import compapp.composeapp.generated.resources.contact
import compapp.composeapp.generated.resources.history
import compapp.composeapp.generated.resources.ic_fullstar
import compapp.composeapp.generated.resources.ic_halfstar
import compapp.composeapp.generated.resources.ic_service
import compapp.composeapp.generated.resources.ic_task
import compapp.composeapp.generated.resources.logout
import compapp.composeapp.generated.resources.menu
import compapp.composeapp.generated.resources.my_tasks
import compapp.composeapp.generated.resources.options
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.ka7suk0sail.compapp.Models.Data.Favor
import org.ka7suk0sail.compapp.Themes.BackgroundLightYellow
import org.ka7suk0sail.compapp.Themes.PrimaryBlack
import org.ka7suk0sail.compapp.Themes.SecondaryLightYellow
import org.ka7suk0sail.compapp.Themes.ThirdLightYellow
import org.ka7suk0sail.compapp.uiScreens.Screen
import org.ka7suk0sail.compapp.Models.Data.Education
import org.ka7suk0sail.compapp.Models.Data.Ride
import org.ka7suk0sail.compapp.Themes.PrimaryYellow
import org.ka7suk0sail.compapp.Themes.ThirdDarkYellow
import org.ka7suk0sail.compapp.ViewModels.HomeViewModel

@Composable
fun FavorItem(favor: Favor, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ThirdLightYellow),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(end = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = favor.authorImage,
                    contentDescription = "Imagen de ${favor.authorName}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(48.dp)
                        .aspectRatio(1 / 1f)
                        .clip(MaterialTheme.shapes.large)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = favor.name,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 18.sp, fontWeight = FontWeight.Medium,
                    color = PrimaryBlack
                )
            }
            Image(
                painter = painterResource(if (favor.isService) Res.drawable.ic_service else Res.drawable.ic_task),
                contentDescription = null,
                modifier = Modifier.size(35.dp).align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
fun EducationItem(education: Education, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ThirdLightYellow),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(end = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = education.authorImage,
                    contentDescription = "Imagen de ${education.authorName}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(48.dp)
                        .aspectRatio(1 / 1f)
                        .clip(MaterialTheme.shapes.large)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = education.name,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryBlack
                )
            }
            Image(
                painter = painterResource(
                    if (education.isService) {
                        Res.drawable.ic_service
                    } else {
                        Res.drawable.ic_task
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
fun RideItem(ride: Ride, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = ThirdLightYellow),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(end = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ride.authorImage,
                    contentDescription = "Imagen de ${ride.authorName}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(48.dp)
                        .aspectRatio(1 / 1f)
                        .clip(MaterialTheme.shapes.large)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = ride.name,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = PrimaryBlack
                )
            }
            Image(
                painter = painterResource(
                    if (ride.isService) {
                        Res.drawable.ic_service
                    } else {
                        Res.drawable.ic_task
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}


