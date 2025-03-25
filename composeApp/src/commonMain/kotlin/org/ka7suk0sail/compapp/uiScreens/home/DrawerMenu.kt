package org.ka7suk0sail.compapp.uiScreens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compapp.composeapp.generated.resources.Res
import compapp.composeapp.generated.resources.avatar
import compapp.composeapp.generated.resources.configuration
import compapp.composeapp.generated.resources.contact
import compapp.composeapp.generated.resources.history
import compapp.composeapp.generated.resources.ic_fullstar
import compapp.composeapp.generated.resources.ic_halfstar
import compapp.composeapp.generated.resources.logout
import compapp.composeapp.generated.resources.options
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.ka7suk0sail.compapp.Themes.BackgroundLightYellow
import org.ka7suk0sail.compapp.Themes.PrimaryBlack
import org.ka7suk0sail.compapp.Themes.SecondaryLightYellow

@Composable
fun DrawerMenu() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SecondaryLightYellow)
            .padding(15.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            ProfileSection()
            RatingSection()
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 8.dp).height(1.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            OptionsSection()
        }
    }
}

@Composable
fun ProfileSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.avatar),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = "[USERNAME]",
            fontSize = 25.sp,
            color = PrimaryBlack
        )
    }
}

@Composable
fun RatingSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(48.dp))
        Text(
            text = "4.7",
            fontSize = 15.sp,
            color = PrimaryBlack
        )

        Spacer(modifier = Modifier.width(8.dp))

        val stars = listOf(
            Res.drawable.ic_fullstar,
            Res.drawable.ic_fullstar,
            Res.drawable.ic_fullstar,
            Res.drawable.ic_fullstar,
            Res.drawable.ic_halfstar
        )

        stars.forEach { starRes ->
            Image(
                painter = painterResource(starRes),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
fun OptionsSection() {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 16.dp)
    ) {
        MenuItem(
            icon = Icons.Rounded.Settings,
            text = stringResource(Res.string.options),
            onClick = { /* TODO */ }
        )

        Spacer(modifier = Modifier.height(8.dp))

        MenuCard(text = stringResource(Res.string.history), onClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(2.dp))
        MenuCard(text = stringResource(Res.string.configuration), onClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(2.dp))
        MenuCard(text = stringResource(Res.string.contact), onClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(2.dp))
        MenuCard(text = stringResource(Res.string.logout), onClick = { /* TODO */ })
    }
}

@Composable
fun MenuItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(30.dp))
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = text,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryBlack
        )
    }
}

@Composable
fun MenuCard(text: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        colors = CardColors(
            containerColor = BackgroundLightYellow,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = BackgroundLightYellow
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            fontSize = 23.sp,
            color = PrimaryBlack,
            modifier = Modifier.padding(10.dp)
        )
    }
}
