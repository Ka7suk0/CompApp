package org.ka7suk0sail.compapp.uiScreens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compapp.composeapp.generated.resources.Res
import compapp.composeapp.generated.resources.compapp_logo
import compapp.composeapp.generated.resources.ic_error
import compapp.composeapp.generated.resources.ic_group
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.painterResource
import org.ka7suk0sail.compapp.Themes.BackgroundLightYellow
import org.ka7suk0sail.compapp.Themes.PrimaryBlack
import org.ka7suk0sail.compapp.Themes.PrimaryNavy
import org.ka7suk0sail.compapp.Themes.PrimaryYellow
import org.ka7suk0sail.compapp.Themes.SecondaryDarkYellow
import org.ka7suk0sail.compapp.Themes.SecondaryLightYellow
import org.ka7suk0sail.compapp.Themes.ThirdDarkYellow
import org.ka7suk0sail.compapp.uiScreens.home.TaskRequest
import org.ka7suk0sail.compapp.uiScreens.home.addRide
import org.ka7suk0sail.compapp.uiScreens.home.sendRequest

val client = HttpClient()

@Serializable
data class loginInfo(
    val email: String?,
    val password: String?
)

@Composable
fun LoginScreen(
    toHomeScreen: () -> Unit,
    toSignUpScreen: () -> Unit
) {
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    Column (modifier = Modifier.background(SecondaryLightYellow)){
        Row (modifier = Modifier.fillMaxWidth().background(PrimaryYellow),
            horizontalArrangement = Arrangement.Center,
            ){
            Spacer(modifier = Modifier.size(16.dp))
            Image(
                painter = painterResource(Res.drawable.compapp_logo),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))

        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item { Body(Modifier.fillMaxWidth().padding(8.dp), toHomeScreen) }
            item { Footer(Modifier.fillMaxWidth().padding(8.dp), toSignUpScreen) }
        }
    }
}

@Composable
fun ErrorDialog(errorMessage: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Ha ocurrido un error", color = PrimaryBlack, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(Res.drawable.ic_error),
                    contentDescription = "Correo enviado",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text("Código de error:", color = PrimaryBlack)
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = errorMessage,
                    color = SecondaryDarkYellow,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = PrimaryBlack)
            ) {
                Text("Aceptar")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(BackgroundLightYellow)
    )
}

@Composable
fun Body(modifier: Modifier, toHomeScreen: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isLoginEnabled by rememberSaveable { mutableStateOf(false) }
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Logo()
            Spacer(modifier = Modifier.size(16.dp))
            TextField(
                value = email,
                onValueChange = {
                    email = it
                    isLoginEnabled = enableLogin(email, password)
                                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .background(BackgroundLightYellow)
                    .padding(3.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = PrimaryBlack,
                    unfocusedTextColor = PrimaryBlack,
                    focusedPlaceholderColor = ThirdDarkYellow,
                    unfocusedPlaceholderColor = ThirdDarkYellow,
                    focusedContainerColor = BackgroundLightYellow,
                    unfocusedContainerColor = BackgroundLightYellow),
                placeholder = { Text("Correo institucional") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )
            Spacer(modifier = Modifier.size(8.dp))
            TextField(
                value = password,
                onValueChange = {
                    password = it
                    isLoginEnabled = enableLogin(email, password)
                                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .background(BackgroundLightYellow)
                    .padding(3.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = PrimaryBlack,
                    unfocusedTextColor = PrimaryBlack,
                    focusedPlaceholderColor = ThirdDarkYellow,
                    unfocusedPlaceholderColor = ThirdDarkYellow,
                    focusedContainerColor = BackgroundLightYellow,
                    unfocusedContainerColor = BackgroundLightYellow),
                placeholder = { Text("Contraseña") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.size(8.dp))
            ForgotPasswordButton(email = email, Modifier.align(Alignment.End), isLoginEnabled)
            Spacer(modifier = Modifier.size(16.dp))
            LoginButton(email = email, password = password, isLoginEnabled, toHomeScreen)
            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun Footer(modifier: Modifier, toSignUpScreen: () -> Unit) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        SignUp(toSignUpScreen)
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun Logo() {
    val image: Painter = painterResource(Res.drawable.ic_group)
    Image(
        painter = image,
        contentDescription = "Logo",
        modifier = Modifier.size(150.dp)
    )
}

@Composable
fun ForgotPasswordButton(email: String, modifier: Modifier, isLoginEnabled: Boolean) {
    TextButton(onClick = { forgotPassword(email, onSuccess = { }, onError = {  }) }, enabled = isLoginEnabled) {
        Text(
            text = "Olvidé la contraseña",
            textAlign = TextAlign.End,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier,
            color = PrimaryBlack
        )
    }
}

@Composable
fun LoginButton(email: String?, password: String?, isLoginEnabled: Boolean, toHomeScreen: () -> Unit) {
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }
    Button(
        onClick = {
            login(
                email = email,
                password = password,
                onSuccess = {
                    toHomeScreen()
                },
                onError = {error ->
                    errorMessage = error // Guardar el mensaje de error
                    showErrorDialog = true
                }
            )
             },
        enabled = isLoginEnabled,
        colors = ButtonDefaults.buttonColors(
            contentColor = PrimaryBlack,
            containerColor = PrimaryYellow
        )) {
        Text(text = "Iniciar Sesión")
    }

    if (showErrorDialog) {
        org.ka7suk0sail.compapp.uiScreens.signup.ErrorDialog(
            errorMessage = errorMessage,
            onDismiss = { showErrorDialog = false })
    }
}

@Composable
fun SignUp(toSignUpScreen: () -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "No tienes una cuenta?  |  ", fontSize = 12.sp)
        Button(onClick = {toSignUpScreen()},
            colors = ButtonDefaults.buttonColors(contentColor = ThirdDarkYellow, containerColor = BackgroundLightYellow)){
            Text(text = "Crear cuenta", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

fun forgotPassword(email: String, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response: HttpResponse = client.post {
                url("https://your-api-url.com/forgotPassword") /* TODO */
                parameter("email", email)
            }
            if (response.status.value in 200..299) {
                onSuccess()
            } else {
                onError(Exception("Failed with status: ${response.status}"))
            }
        } catch (e: Throwable) {
            onError(e)
        }
    }
}

fun enableLogin(email: String, password: String): Boolean {
return (isValidEmail(email) && password.length >= 6)
}

fun isValidEmail(email: String): Boolean {
val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
return email.matches(emailRegex.toRegex())
}

fun login(email: String?, password: String?, onSuccess: () -> Unit, onError: (String) -> Unit){
val loginInfo = loginInfo(
    email = email,
    password = password
)

sendLogin(loginInfo, onSuccess, onError)
}

fun sendLogin(loginInfo: loginInfo, onSuccess: () -> Unit, onError: (String) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response: HttpResponse = client.post {
                url("http://192.168.170.91:8080/api/users/login") // TODO: Colocar la URL real
                contentType(ContentType.Application.Json) // Establecer el Content-Type
                setBody(Json.encodeToString(loginInfo))
            }

            if (response.status.value in 200..299) {
                // Cambiar al hilo principal antes de actualizar la UI
                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } else {
                // Cambiar al hilo principal antes de actualizar la UI
                withContext(Dispatchers.Main) {
                    onError("Error: ${response.status.value} - ${response.status.description}")
                }
            }
        } catch (e: Throwable) {
            // Cambiar al hilo principal antes de actualizar la UI
            withContext(Dispatchers.Main) {
                onError("Error de red: ${e.message}")
            }
        }
    }
}
