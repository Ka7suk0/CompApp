package org.ka7suk0sail.compapp.Models.Utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult<T>(
    val page: Int,
    val results: List<T>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)

@Serializable
data class RemoteFavor(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("release_date") val releaseDate : String,
    @SerialName("poster_path") val posterPath : String,
    @SerialName("backdrop_path") val backdropPath : String?,
    @SerialName("original_title") val originalTitle : String,
    @SerialName("original_language") val originalLanguage : String,
    val popularity: Double,
    @SerialName("vote_average") val voteAverage : Double
)

@Serializable
data class RemoteEducation(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_language") val originalLanguage: String,
    val popularity: Double,
    @SerialName("vote_average") val voteAverage: Double
)

@Serializable
data class RemoteRide(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_language") val originalLanguage: String,
    val popularity: Double,
    @SerialName("vote_average") val voteAverage: Double
)