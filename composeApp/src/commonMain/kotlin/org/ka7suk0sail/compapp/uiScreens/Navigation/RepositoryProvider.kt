package org.ka7suk0sail.compapp.uiScreens.Navigation
/*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import compapp.composeapp.generated.resources.Res
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource
import org.ka7suk0sail.compapp.Models.Repository.EducationsRepository
import org.ka7suk0sail.compapp.Models.Repository.FavorsRepository
import org.ka7suk0sail.compapp.Models.Repository.RidesRepository
import org.ka7suk0sail.compapp.Models.Service.EducationsService
import org.ka7suk0sail.compapp.Models.Service.FavorsService
import org.ka7suk0sail.compapp.Models.Service.RidesService

@Composable
fun rememberFavorsRepository(
    apiKey: String = stringResource(Res.string.api_key)
): FavorsRepository = remember {
    val client =
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org"
                    parameters.append("api_key", apiKey)
                }
            }
        }
    FavorsRepository(FavorsService(client))
}

@Composable
fun rememberEducationsRepository(
    apiKey: String = stringResource(Res.string.api_key)
): EducationsRepository = remember {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.themoviedb.org"
                parameters.append("api_key", apiKey)
            }
        }
    }
    EducationsRepository(EducationsService(client))
}

@Composable
fun rememberRidesRepository(
    apiKey: String = stringResource(Res.string.api_key)
): RidesRepository = remember {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.themoviedb.org"
                parameters.append("api_key", apiKey)
            }
        }
    }
    RidesRepository(RidesService(client))
}
*/