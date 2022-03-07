package ovh.vicart.ideas.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ovh.vicart.ideas.repositories.api.IdeasAPI

object NetworkRepository {

    internal val api = IdeasAPI()

    object PreferenceKeys {
        val tokenKey = stringPreferencesKey("token")
    }

    internal val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "data")
}