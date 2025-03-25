package org.ka7suk0sail.compapp.uiScreens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compapp.composeapp.generated.resources.Res
import compapp.composeapp.generated.resources.compapp_logo
import compapp.composeapp.generated.resources.ic_error
import compapp.composeapp.generated.resources.ic_mail
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import kotlinx.serialization.json.encodeToJsonElement
import org.jetbrains.compose.resources.painterResource
import org.ka7suk0sail.compapp.Themes.*
import org.ka7suk0sail.compapp.uiScreens.home.TaskRequest
import org.ka7suk0sail.compapp.uiScreens.home.sendRequest
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json



val client = HttpClient{
    install(ContentNegotiation) {
        json() // Configurar serialización JSON
    }
}

@Serializable
data class NewUser(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val studentId: String? = null,
    val role: String? = null
)

@Composable
fun SignUpScreen(
    onBack: () -> Unit // Manejo de la navegación hacia atrás
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    Column(modifier = Modifier.background(SecondaryLightYellow)) {
        // Encabezado
        Header()

        // Formulario de registro scrolleable
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                SignUpForm(onBack = onBack, onSignUpSuccess = { showDialog = true })
            }
        }
        if (showDialog) {
            SignUpSuccessDialog(onDismiss = {
                showDialog = false
                onBack() // Vuelve al login después del registro
            })
        }

    }
}

@Composable
fun SignUpSuccessDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "¡Gracias por registrarte!", color = PrimaryBlack, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Revisa tu correo electrónico.", color = PrimaryBlack)
                Spacer(modifier = Modifier.size(16.dp))
                Image(
                    painter = painterResource(Res.drawable.ic_mail),
                    contentDescription = "Correo enviado",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "Al hacer click en el botón que aparece en el Email, activarás tu cuenta y estarás listo para disfrutar de CompApp.",
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
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PrimaryYellow),
        horizontalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(Res.drawable.compapp_logo),
            contentDescription = "Logo",
            modifier = Modifier.width(150.dp).padding(30.dp)
        )
    }
}

@Composable
fun SignUpForm(onBack: () -> Unit, onSignUpSuccess: () -> Unit) {
    var showErrorDialog by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var matricula by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var userType by rememberSaveable { mutableStateOf("Alumno") }
    var profilePhoto by rememberSaveable { mutableStateOf("No has seleccionado ningún archivo.") }
    var idCardFront by rememberSaveable { mutableStateOf("No has seleccionado ningún archivo.") }
    var idCardBack by rememberSaveable { mutableStateOf("No has seleccionado ningún archivo.") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = PrimaryBlack)

        // Selección de tipo de usuario (Alumno o Profesor)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            RadioButton(
                selected = userType == "Alumno",
                onClick = { userType = "Alumno" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = ThirdDarkYellow,
                    unselectedColor = ThirdDarkYellow
                )
            )
            Text(
                text = "Alumno",
                color = PrimaryBlack,
                modifier = Modifier
                    .clickable { userType = "Alumno" }
            )

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = userType == "Profesor",
                onClick = { userType = "Profesor" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = ThirdDarkYellow,
                    unselectedColor = ThirdDarkYellow
                )
            )
            Text(
                text = "Profesor",
                color = PrimaryBlack,
                modifier = Modifier
                    .clickable { userType = "Profesor" }
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        // Campos de texto
        SignUpTextField(value = name, label = "Nombre(s)", onValueChange = { name = it })
        SignUpTextField(value = lastName, label = "Apellidos", onValueChange = { lastName = it })
        SignUpTextField(value = matricula, label = "Matrícula", onValueChange = { matricula = it })
        SignUpTextField(value = email, label = "Correo institucional", onValueChange = { email = it }, keyboardType = KeyboardType.Email)
        SignUpTextField(value = password, label = "Contraseña", onValueChange = { password = it }, keyboardType = KeyboardType.Password)

        Spacer(modifier = Modifier.size(16.dp))

        // Selección de archivos
        FilePicker(label = "Sube una fotografía de perfil", fileName = profilePhoto, onFileSelected = { profilePhoto = it })
        FilePicker(label = "Sube una fotografía de tu credencial (frente)", fileName = idCardFront, onFileSelected = { idCardFront = it })
        FilePicker(label = "Sube una fotografía de tu credencial (detrás)", fileName = idCardBack, onFileSelected = { idCardBack = it })

        Spacer(modifier = Modifier.size(16.dp))

        // Botón de registro
        Button(
            onClick = {
                sendNewUser(
                    name = name + " " + lastName,
                    email = email,
                    password = password,
                    studentId = matricula,
                    role = if(userType == "Alumno"){"STUDENT"}else{"TEACHER"},
                    onSuccess = {
                        onSignUpSuccess()
                    },
                    onError = {error ->
                        errorMessage = error // Guardar el mensaje de error
                        showErrorDialog = true
                    }
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow, contentColor = PrimaryBlack),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }
        if (showErrorDialog) {
            ErrorDialog(errorMessage = errorMessage, onDismiss = { showErrorDialog = false })
        }
    }
}

@Composable
fun SignUpTextField(value: String, label: String, onValueChange: (String) -> Unit, keyboardType: KeyboardType = KeyboardType.Text) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .padding(3.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = PrimaryBlack,
            unfocusedTextColor = PrimaryBlack,
            focusedPlaceholderColor = ThirdDarkYellow,
            unfocusedPlaceholderColor = ThirdDarkYellow,
            focusedContainerColor = BackgroundLightYellow,
            unfocusedContainerColor = BackgroundLightYellow
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        maxLines = 1
    )
}

@Composable
fun FilePicker(label: String, fileName: String, onFileSelected: (String) -> Unit) {
    var selectedArchive by remember { mutableStateOf(false) }  // Crear un estado observable

    Column {
        Text(label, color = PrimaryBlack)
        Spacer(modifier = Modifier.size(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = {
                    selectedArchive = true // Actualizar el estado cuando se presiona el botón
                    onFileSelected("Photo1234.jpg") // Ejecutar callback para cambiar el valor externo si es necesario
                },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryWhite, contentColor = PrimaryBlack)
            ) {
                Text("Examinar")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = if (selectedArchive) "Photo1234.jpg" else fileName,
                color = ThirdDarkYellow
            )
        }
    }
}

fun sendNewUser(
    name : String,
    email : String?,
    password : String?,
    studentId : String?,
    role : String?,
    onSuccess : () -> Unit,
    onError : (String) -> Unit
) {
    val newUser = NewUser(
        name = name,
        email = email,
        password = password,
        studentId = studentId,
        role = role
    )

    sendNewUserRequest(newUser, onSuccess, onError)
}

fun sendNewUserRequest(newUser: NewUser, onSuccess: () -> Unit, onError: (String) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response: HttpResponse = client.post {
                url("http://192.168.170.91:8080/api/users/register") /* TODO */
                contentType(ContentType.Application.Json) // Establecer el Content-Type
                setBody(Json.encodeToString(newUser))
            }
            if (response.status.value in 200..299) {
                onSuccess()
            } else {
                onError("Error: ${response.status.value} - ${response.status.description}")
            }
        } catch (e: Throwable) {
            onError("Error de red: ${e.message}")
        }
    }

}