package ovh.vicart.ideas.repositories.api

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import ovh.vicart.ideas.models.Idea
import ovh.vicart.ideas.models.LoginResponse
import ovh.vicart.ideas.models.User
import ovh.vicart.ideas.util.Utils

class IdeasAPI {

    private val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
    private val url = Utils.API_BASE_URL

    suspend fun getIdeas(token: String) : List<Idea> {
        return client.get("$url/ideas") {
            header("authorization", "Bearer $token")
        }
    }

    suspend fun login(user: User) : LoginResponse {
        return client.post("$url/login") {
            body = user
            header("Content-Type", "application/json")
        }
    }

    suspend fun check(token: String) : Boolean {
        return true
    }
}