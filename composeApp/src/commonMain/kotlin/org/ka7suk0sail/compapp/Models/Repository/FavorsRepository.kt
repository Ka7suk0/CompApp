package org.ka7suk0sail.compapp.Models.Repository

import org.ka7suk0sail.compapp.Models.Data.Favor
import org.ka7suk0sail.compapp.Models.Service.FavorsService
import org.ka7suk0sail.compapp.Models.Utils.RemoteFavor

class FavorsRepository(private val favorsService: FavorsService) {
    suspend fun fetchPopularFavors(): List<Favor>{
        return favorsService.fetchPopularFavors().results.map{it.toDomainFavor()}
    }

    suspend fun fetchFavorById(id: Int): Favor {
        return favorsService.fetchFavorById(id).toDomainFavor()
    }
}

private fun RemoteFavor.toDomainFavor() = Favor(
    favorID = id,
    name = title,
    description = overview,
    authorImage = "https://image.tmdb.org/t/p/w185/$posterPath",
    datePosted = releaseDate
    /*
    backdrop = backdropPath?.let {"https://image.tmdb.org/t/p/w780/$it"},
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity,
    voteAverage = voteAverage
    */
)