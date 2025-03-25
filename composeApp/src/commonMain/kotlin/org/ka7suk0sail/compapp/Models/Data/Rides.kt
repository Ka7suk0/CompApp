package org.ka7suk0sail.compapp.Models.Data

data class Ride(
    val rideID: Int = -1,
    val datePosted: String = "",
    val name: String = "",
    val description: String = "",
    val authorImage: String = "",
    val isService: Boolean = false,
    val authorName: String = "",
)