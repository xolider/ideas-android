package ovh.vicart.ideas.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val username: String, val password: String)