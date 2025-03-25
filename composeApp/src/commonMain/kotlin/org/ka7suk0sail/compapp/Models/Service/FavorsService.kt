package org.ka7suk0sail.compapp.Models.Service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.ka7suk0sail.compapp.Models.Utils.RemoteFavor
import org.ka7suk0sail.compapp.Models.Utils.RemoteResult

class FavorsService (
    private val client: HttpClient
){
    suspend fun fetchPopularFavors(): RemoteResult<RemoteFavor> {
        return client
            .get("/3/discover/movie?sort_by=popularity.desc")
            .body<RemoteResult<RemoteFavor>>()
    }

    suspend fun fetchFavorById(id: Int): RemoteFavor {
        return client
            .get("/3/movie/$id")
            .body<RemoteFavor>()
    }
}