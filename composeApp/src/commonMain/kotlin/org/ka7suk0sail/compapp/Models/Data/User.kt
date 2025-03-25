package org.ka7suk0sail.compapp.Models.Data

data class User(
    val id : String = "", /* Matrícula / Num de Empleado */
    val name : String = "",
    val rol : String = "", /* STUDENT / TEACHER */
    val email : String = "",
    val password : String = "",
    val profilePicture : String = "",
    val idFrontPicture: String = ""
)

val users = (1..10).map{
    User(
        id = "$it",
        name = "Favor $it"
    )
}