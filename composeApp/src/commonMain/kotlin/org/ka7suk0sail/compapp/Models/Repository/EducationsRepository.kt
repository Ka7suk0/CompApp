package org.ka7suk0sail.compapp.Models.Repository

import org.ka7suk0sail.compapp.Models.Data.Education
import org.ka7suk0sail.compapp.Models.Service.EducationsService
import org.ka7suk0sail.compapp.Models.Utils.RemoteEducation

class EducationsRepository(private val educationsService: EducationsService) {
    suspend fun fetchPopularEducations(): List<Education> {
        return educationsService.fetchPopularEducations().results.map { it.toDomainEducation() }
    }

    suspend fun fetchEducationById(id: Int): Education {
        return educationsService.fetchEducationById(id).toDomainEducation()
    }
}

private fun RemoteEducation.toDomainEducation() = Education(
    educationID = id,
    name = title,
    description = overview,
    authorImage = "https://image.tmdb.org/t/p/w185/$posterPath",
    datePosted = releaseDate
)