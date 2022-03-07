package ovh.vicart.ideas.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import ovh.vicart.ideas.models.User
import ovh.vicart.ideas.repositories.NetworkRepository.api
import ovh.vicart.ideas.repositories.NetworkRepository.dataStore

object UsersRepository {

    suspend fun login(context: Context, user: User) {
        val response = api.login(user)
        val token = response.token
        context.dataStore.edit {
            it[NetworkRepository.PreferenceKeys.tokenKey] = token
        }
    }

    suspend fun isConnected(context: Context) : Boolean {
        val token = context.dataStore.data.map {
            it[NetworkRepository.PreferenceKeys.tokenKey]
        }.firstOrNull() ?: return false
        return api.check(token)
    }

    suspend fun logout(context: Context) {
        context.dataStore.edit {
            it.remove(NetworkRepository.PreferenceKeys.tokenKey)
        }
    }
}