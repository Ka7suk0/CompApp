package org.ka7suk0sail.compapp.uiScreens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.ka7suk0sail.compapp.Themes.CustomTheme

@Composable
fun Screen(modifier: Modifier = Modifier, content : @Composable () -> Unit){
    CustomTheme{
        Surface (
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}