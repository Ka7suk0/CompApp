package org.ka7suk0sail.compapp.Models.Service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.ka7suk0sail.compapp.Models.Utils.RemoteResult
import org.ka7suk0sail.compapp.Models.Utils.RemoteRide

class RidesService(
    private val client: HttpClient
) {
    suspend fun fetchPopularRides(): RemoteResult<RemoteRide> {
        return client
            .get("/3/discover/movie?sort_by=popularity.desc")
            .body<RemoteResult<RemoteRide>>()
    }

    suspend fun fetchRideById(id: Int): RemoteRide {
        return client
            .get("/3/movie/$id")
            .body<RemoteRide>()
    }
}