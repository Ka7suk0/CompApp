package org.ka7suk0sail.compapp.uiScreens.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compapp.composeapp.generated.resources.Res
import compapp.composeapp.generated.resources.avatar
import compapp.composeapp.generated.resources.compapp_colored_logo
import compapp.composeapp.generated.resources.ic_car
import compapp.composeapp.generated.resources.ic_diversity
import compapp.composeapp.generated.resources.ic_exchange
import compapp.composeapp.generated.resources.ic_food
import compapp.composeapp.generated.resources.ic_savings
import compapp.composeapp.generated.resources.ic_secure
import compapp.composeapp.generated.resources.ic_study
import compapp.composeapp.generated.resources.ic_task
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.ka7suk0sail.compapp.Themes.BackgroundLightYellow
import org.ka7suk0sail.compapp.Themes.PrimaryBlack
import org.ka7suk0sail.compapp.Themes.PrimaryGray
import org.ka7suk0sail.compapp.Themes.PrimaryYellow
import org.ka7suk0sail.compapp.Themes.ThirdLightYellow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    toLoginScreen: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ThirdLightYellow)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        // Skip Button
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {

            Button(
                onClick = { toLoginScreen() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (pagerState.currentPage < 2){BackgroundLightYellow}else{Color.Transparent},
                    contentColor = if (pagerState.currentPage < 2){PrimaryGray}else{Color.Transparent}
                ),
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Saltar")
            }
        }

        // Horizontal Pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> WelcomePage1()
                1 -> WelcomePage2()
                2 -> WelcomePage3()
            }
        }

        // Bottom Navigation with Previous, Next, and Radio Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    if (pagerState.currentPage > 0) {
                        coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (pagerState.currentPage > 0){BackgroundLightYellow}else{Color.Transparent},
                    contentColor = if (pagerState.currentPage > 0){PrimaryBlack}else{Color.Transparent}
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = "Anterior")
            }

            Spacer(modifier = Modifier.weight(1f))

            // Radio buttons for pagination indicators
            RadioButtons(
                currentPage = pagerState.currentPage,
                totalPages = 3
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (pagerState.currentPage < 2) {
                        coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    } else {
                        toLoginScreen()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundLightYellow,
                    contentColor = PrimaryBlack
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(text = if (pagerState.currentPage == 2) "Comenzar" else "Siguiente")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun RadioButtons(currentPage: Int, totalPages: Int) {
    Row {
        for (i in 0 until totalPages) {
            RadioButton(
                selected = i == currentPage,
                onClick = null,
                enabled = false,
                modifier = Modifier.scale(0.7f)
            )
        }
    }
}

@Composable
fun WelcomePage1() {
    WelcomePageDesign1(
        drawableResource1 = Res.drawable.compapp_colored_logo,
        title = "¡Bienvenido a CompApp!",
        description = "Una aplicación que busca la colaboración y el apoyo mutuo entre estudiantes y maestros de la universidad",
        drawableResource2 = Res.drawable.ic_diversity
    )
}

@Composable
fun WelcomePage2() {
    WelcomePageDesign2(
        styleInverted = false,
        title = "Propósito",
        text1 = "Tal vez no tuviste tiempo de comprar algo de comer",
        text2 = "O puede ser que necesites ayuda con alguna tarea o para estudiar para algún examen",
        text3 = "También puedes necesitar que alguien te apoye con transporte a la universidad",
        drawableResource1 = Res.drawable.ic_food,
        drawableResource2 = Res.drawable.ic_study,
        drawableResource3 = Res.drawable.ic_car,
        bottomtext = "¡Todo esto lo puedes encontrar en CompApp!"
    )
}

@Composable
fun WelcomePage3() {
    WelcomePageDesign2(
        styleInverted = true,
        title = "Una red de intercambios, favores y apoyo",
        text1 = "Siempre puedes encontrar a alguien cerca que esté dispuesto a apoyarte en lo que necesites",
        text2 = "Además, puedes obtener ingresos extras ayudando a los demás u ofreciendo tu servicio o producto",
        text3 = "Todo esto en un entorno seguro, ya que los usuarios pertenecen a la universidad, cada cuenta está verificada",
        drawableResource1 = Res.drawable.ic_exchange,
        drawableResource2 = Res.drawable.ic_savings,
        drawableResource3 = Res.drawable.ic_secure,
        bottomtext = "¡Comienza a apoyar y conoce compas con CompApp!"
    )
}


@Composable
fun WelcomePageDesign1(
    drawableResource1: DrawableResource,
    title: String,
    description: String,
    drawableResource2: DrawableResource
) {
    Card (
        modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundLightYellow),
        shape = RoundedCornerShape(30.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Card (modifier = Modifier.size(200.dp),
                shape = RoundedCornerShape(30.dp)
            ){  Image(
                painter = painterResource(resource = drawableResource1),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            ) }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlack
            )
            Text(
                text = description,
                fontSize = 18.sp,
                color = PrimaryBlack,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(resource = drawableResource2),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
fun WelcomePageDesign2(
    styleInverted: Boolean,
    title: String,
    text1: String,
    text2: String,
    text3: String,
    bottomtext: String,
    drawableResource1: DrawableResource,
    drawableResource2: DrawableResource,
    drawableResource3: DrawableResource
) {
    Card (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundLightYellow),
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(BackgroundLightYellow),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlack,
                modifier = Modifier.padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(!styleInverted){
                    Text(
                        text = text1,
                        fontSize = 18.sp,
                        color = PrimaryBlack,
                        modifier = Modifier.padding(8.dp).weight(2f),
                        textAlign = TextAlign.Center
                    )
                }
                Image(
                    painter = painterResource(resource = drawableResource1),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                )
                if(styleInverted){
                    Text(
                        text = text1,
                        fontSize = 18.sp,
                        color = PrimaryBlack,
                        modifier = Modifier.padding(8.dp).weight(2f),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(styleInverted){
                    Text(
                        text = text2,
                        fontSize = 18.sp,
                        color = PrimaryBlack,
                        modifier = Modifier.padding(8.dp).weight(2f),
                        textAlign = TextAlign.Center
                    )
                }
                Image(
                    painter = painterResource(resource = drawableResource2),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                )
                if(!styleInverted){
                    Text(
                        text = text2,
                        fontSize = 18.sp,
                        color = PrimaryBlack,
                        modifier = Modifier.padding(8.dp).weight(2f),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(!styleInverted){
                    Text(
                        text = text3,
                        fontSize = 18.sp,
                        color = PrimaryBlack,
                        modifier = Modifier.padding(8.dp).weight(2f),
                        textAlign = TextAlign.Center
                    )
                }
                Image(
                    painter = painterResource(resource = drawableResource3),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                )
                if(styleInverted){
                    Text(
                        text = text3,
                        fontSize = 18.sp,
                        color = PrimaryBlack,
                        modifier = Modifier.padding(8.dp).weight(2f),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = bottomtext,
                fontSize = 22.sp,
                color = PrimaryBlack,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}
