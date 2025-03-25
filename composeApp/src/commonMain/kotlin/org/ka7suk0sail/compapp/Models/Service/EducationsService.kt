package org.ka7suk0sail.compapp.Models.Service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.ka7suk0sail.compapp.Models.Utils.RemoteEducation
import org.ka7suk0sail.compapp.Models.Utils.RemoteResult

class EducationsService(
    private val client: HttpClient
) {
    suspend fun fetchPopularEducations(): RemoteResult<RemoteEducation> {
        return client
            .get("/3/discover/movie?sort_by=popularity.desc")
            .body<RemoteResult<RemoteEducation>>()
    }

    suspend fun fetchEducationById(id: Int): RemoteEducation {
        return client
            .get("/3/movie/$id")
            .body<RemoteEducation>()
    }
}