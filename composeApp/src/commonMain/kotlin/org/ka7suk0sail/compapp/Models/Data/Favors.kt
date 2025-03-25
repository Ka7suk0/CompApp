package org.ka7suk0sail.compapp.Models.Data

data class Favor (
    val favorID: Int =-1, /* Id del encargo */
    val datePosted: String = "", /* Fecha y hora posteado */
    val name: String = "", /* Nombre del encargo / categoría */
    val description: String = "", /* Descripción detallada del encargo */
    val place:String = "", /* Ubicación de entrega / requerimiento */
    val isService: Boolean = false, /* false = necesidad, true = servicio */
    val pay: Int = -1, /* Costo $ */
    val authorID: Int = -1, /* ID del posteador */
    val authorName: String = "",
    val authorImage : String = "",
    val isCanceled: Boolean = false,
    val isAccepted: Boolean = false, /* Ya fue asignada: true/false */
    val dateAccepted: String = "", /* Fecha y hora aceptado */
    val assignedID: Int = -1, /* ID de la persona que se asigna */
    val assignedName: Int = -1, /* nombre de la persona que se asigna */
    val isCompleted: Boolean = false, /* Ya fue completado */
    val dateCompleted: String = "", /* Fecha y hora completado */
    val starsAuthor: Int = -1, /* Calificación de cómo fue el favor de acuerdo al posteador */
    val starsResponsible: Int = -1 /* Calificación de cómo fue el favor de acuerdo a la persona que aceptó*/
)

