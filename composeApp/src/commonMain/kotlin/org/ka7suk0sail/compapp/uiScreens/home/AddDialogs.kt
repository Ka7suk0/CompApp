package org.ka7suk0sail.compapp.uiScreens.home

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.IO
import org.ka7suk0sail.compapp.Themes.*

val client = HttpClient()

@Serializable
data class TaskRequest(
    val usuario: String?, /* TODO */
    val isService: Boolean = false,
    val nombre: String? = null,
    val descripcion: String? = null,
    val lugar: String? = null,
    val precio: String? = null,
    val materia: String? = null,
    val hora: String? = null,
    val toSchool: Boolean? = null
)

@Composable
fun FavorDialog(onDismiss: () -> Unit, usuario: String) {
    var selectedOption by remember { mutableStateOf("Solicitud") }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var place by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = SecondaryLightYellow,
            modifier = Modifier
                .width(300.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Nuevo Encargo",
                    color = PrimaryBlack,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Radio Group Replacement
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == "Solicitud",
                        onClick = { selectedOption = "Solicitud" },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ThirdDarkYellow,
                            unselectedColor = ThirdDarkYellow
                        )
                    )
                    Text(
                        text = "Solicitud",
                        color = PrimaryBlack,
                        modifier = Modifier
                            .clickable { selectedOption = "Solicitud" }
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    RadioButton(
                        selected = selectedOption == "Servicio",
                        onClick = { selectedOption = "Servicio" },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ThirdDarkYellow,
                            unselectedColor = ThirdDarkYellow
                        )
                    )
                    Text(
                        text = "Servicio",
                        color = PrimaryBlack,
                        modifier = Modifier
                            .clickable { selectedOption = "Servicio" }
                    )

                }

                // Nombre del encargo
                Text(
                    text = "Nombre del encargo",
                    color = PrimaryBlack,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                TextField(
                    value = name,
                    onValueChange = { name = it },
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
                    placeholder = { Text("p.e. 'Papelería' / 'Taquitos'") }
                )

                // Descripción
                Text(
                    text = "Descripción",
                    color = PrimaryBlack,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
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
                    placeholder = { Text("p.e. 'necesito una hoja papel ministro para las 2pm'") },
                    maxLines = 4
                )

                // Lugar de encuentro
                Text(
                    text = "Lugar de encuentro",
                    color = PrimaryBlack,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                TextField(
                    value = place,
                    onValueChange = { place = it },
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
                    placeholder = { Text("p.e. 'edificio principal' / 'N06'") }
                )

                // Precio
                Text(
                    text = "Precio",
                    color = PrimaryBlack,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Text(
                        text = "$  ",
                        color = PrimaryBlack,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    TextField(
                        value = price,
                        onValueChange = { price = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(BackgroundLightYellow)
                            .padding(3.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = PrimaryBlack,
                            unfocusedTextColor = PrimaryBlack,
                            focusedPlaceholderColor = ThirdDarkYellow,
                            unfocusedPlaceholderColor = ThirdDarkYellow,
                            focusedContainerColor = BackgroundLightYellow,
                            unfocusedContainerColor = BackgroundLightYellow),
                        placeholder = { Text("10") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                // Botón de Aceptar
                Button(
                    onClick = {
                        addFavor(
                            usuario = usuario,
                            isService = selectedOption == "Servicio",
                            nombre = name,
                            descripcion = description,
                            lugar = place,
                            precio = price,
                            onSuccess = {
                                onDismiss()
                            },
                            onError = {
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryYellow,
                        contentColor = PrimaryBlack
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Aceptar", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
fun EducationDialog(onDismiss: () -> Unit, usuario: String) {
    var selectedOption by remember { mutableStateOf("Solicitud") }
    var subject by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = SecondaryLightYellow,
            modifier = Modifier
                .width(300.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Educación",
                    color = PrimaryBlack,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Radio Group Replacement
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == "Solicitud",
                        onClick = { selectedOption = "Solicitud" },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ThirdDarkYellow,
                            unselectedColor = ThirdDarkYellow
                        )
                    )
                    Text(
                        text = "Solicitud",
                        color = PrimaryBlack,
                        modifier = Modifier
                            .clickable { selectedOption = "Solicitud" }
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    RadioButton(
                        selected = selectedOption == "Servicio",
                        onClick = { selectedOption = "Servicio" },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ThirdDarkYellow,
                            unselectedColor = ThirdDarkYellow
                        )
                    )
                    Text(
                        text = "Servicio",
                        color = PrimaryBlack,
                        modifier = Modifier
                            .clickable { selectedOption = "Servicio" }
                    )

                }

                // Materia del encargo
                Text(
                    text = "Materia",
                    color = PrimaryBlack,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                TextField(
                    value = subject,
                    onValueChange = { subject = it },
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
                    placeholder = { Text("p.e. 'Contabilidad' / 'POO'") }
                )

                // Descripción
                Text(
                    text = "Descripción",
                    color = PrimaryBlack,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
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
                    placeholder = { Text("p.e. 'Necesito apuntes de la materia' / 'Necesito asesorías'") },
                    maxLines = 4
                )

                // Precio
                Text(
                    text = "Precio",
                    color = PrimaryBlack,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Text(
                        text = "$  ",
                        color = PrimaryBlack,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    TextField(
                        value = price,
                        onValueChange = { price = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(BackgroundLightYellow)
                            .padding(3.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = PrimaryBlack,
                            unfocusedTextColor = PrimaryBlack,
                            focusedPlaceholderColor = ThirdDarkYellow,
                            unfocusedPlaceholderColor = ThirdDarkYellow,
                            focusedContainerColor = BackgroundLightYellow,
                            unfocusedContainerColor = BackgroundLightYellow),
                        placeholder = { Text("p.e. '10' / '70 por hora'") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                // Botón de Aceptar
                Button(
                    onClick = {
                        addEducation(usuario = usuario,
                            isService = selectedOption == "Servicio",
                            materia = subject,
                            descripcion = description,
                            precio = price,
                            onSuccess = {
                                onDismiss()
                            },
                            onError = {
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryYellow,
                        contentColor = PrimaryBlack
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Aceptar", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
fun RideDialog(onDismiss: () -> Unit, usuario: String) {
    var selectedOption by remember { mutableStateOf("Solicitud") }
    var hour by remember { mutableStateOf("") }
    var toSchool by remember { mutableStateOf(true) }
    var place by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = SecondaryLightYellow,
            modifier = Modifier
                .width(300.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Nuevo Ride",
                    color = PrimaryBlack,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Radio Group Replacement
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = selectedOption == "Solicitud",
                        onClick = { selectedOption = "Solicitud" },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ThirdDarkYellow,
                            unselectedColor = ThirdDarkYellow
                        )
                    )
                    Text(
                        text = "Solicitud",
                        color = PrimaryBlack,
                        modifier = Modifier
                            .clickable { selectedOption = "Solicitud" }
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    RadioButton(
                        selected = selectedOption == "Servicio",
                        onClick = { selectedOption = "Servicio" },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ThirdDarkYellow,
                            unselectedColor = ThirdDarkYellow
                        )
                    )
                    Text(
                        text = "Servicio",
                        color = PrimaryBlack,
                        modifier = Modifier
                            .clickable { selectedOption = "Servicio" }
                    )

                }

                // Hora
                Text(
                    text = "Hora (en el Tec)",
                    color = PrimaryBlack,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                TextField(
                    value = hour,
                    onValueChange = { hour = it },
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
                    placeholder = { Text("p.e. '7 am' / '14:00'") }
                )

                // Radio Group Replacement
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = toSchool == true,
                        onClick = { toSchool = true},
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ThirdDarkYellow,
                            unselectedColor = ThirdDarkYellow
                        )
                    )
                    Text(
                        text = "Hacia el Tec",
                        color = PrimaryBlack,
                        modifier = Modifier
                            .clickable { toSchool = true}
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    RadioButton(
                        selected = toSchool == false,
                        onClick = { toSchool = false},
                        colors = RadioButtonDefaults.colors(
                            selectedColor = ThirdDarkYellow,
                            unselectedColor = ThirdDarkYellow
                        )
                    )
                    Text(
                        text = "Desde el Tec",
                        color = PrimaryBlack,
                        modifier = Modifier
                            .clickable { toSchool = false}
                    )

                }
                // Lugar de encuentro
                Text(
                    text = "Lugar de encuentro",
                    color = PrimaryBlack,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                TextField(
                    value = place,
                    onValueChange = { place = it },
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
                    placeholder = { Text("p.e. 'Desde Mirasierra' / 'Hacia Ramos'") }
                )

                // Botón de Aceptar
                Button(
                    onClick = {
                        addRide(
                            usuario = usuario,
                            isService = selectedOption == "Servicio",
                            hora = hour,
                            lugar = place,
                            toSchool = toSchool,
                            onSuccess = {
                                onDismiss()
                            },
                            onError = {
                            }
                        )
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryYellow,
                        contentColor = PrimaryBlack
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Aceptar", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

fun addFavor(
    usuario: String,
    isService: Boolean,
    nombre: String,
    descripcion: String,
    lugar: String,
    precio: String,
    onSuccess: () -> Unit,
    onError: (Throwable) -> Unit
) {
    val taskRequest = TaskRequest(
        usuario = usuario,
        isService = isService,
        nombre = nombre,
        descripcion = descripcion,
        lugar = lugar,
        precio = precio
    )

    sendRequest(taskRequest, onSuccess, onError)
}

fun addEducation(
    usuario: String,
    isService: Boolean,
    materia: String,
    descripcion: String,
    precio: String,
    onSuccess: () -> Unit,
    onError: (Throwable) -> Unit
) {
    val taskRequest = TaskRequest(
        usuario = usuario,
        isService = isService,
        materia = materia,
        descripcion = descripcion,
        precio = precio
    )

    sendRequest(taskRequest, onSuccess, onError)
}

fun addRide(
    usuario: String,
    isService: Boolean,
    hora: String,
    lugar: String,
    toSchool: Boolean,
    onSuccess: () -> Unit,
    onError: (Throwable) -> Unit
) {
    val taskRequest = TaskRequest(
        usuario = usuario,
        isService = isService,
        hora = hora,
        lugar = lugar,
        toSchool = toSchool
    )

    sendRequest(taskRequest, onSuccess, onError)
}

fun sendRequest(taskRequest: TaskRequest, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
    /*
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response: HttpResponse = client.post {
                url("https://your-api-url.com/tasks") /* TODO */
                setBody(Json.encodeToString(taskRequest))
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
    */
}