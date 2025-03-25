package org.ka7suk0sail.compapp.Models.Repository

import org.ka7suk0sail.compapp.Models.Data.Ride
import org.ka7suk0sail.compapp.Models.Service.RidesService
import org.ka7suk0sail.compapp.Models.Utils.RemoteRide

class RidesRepository(private val ridesService: RidesService) {
    suspend fun fetchPopularRides(): List<Ride> {
        return ridesService.fetchPopularRides().results.map { it.toDomainRide() }
    }

    suspend fun fetchRideById(id: Int): Ride {
        return ridesService.fetchRideById(id).toDomainRide()
    }
}

private fun RemoteRide.toDomainRide() = Ride(
    rideID = id,
    name = title,
    description = overview,
    authorImage = "https://image.tmdb.org/t/p/w185/$posterPath",
    datePosted = releaseDate
)
