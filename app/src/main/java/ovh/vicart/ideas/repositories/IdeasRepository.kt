package ovh.vicart.ideas.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.room.Room
import kotlinx.coroutines.flow.*
import ovh.vicart.ideas.models.Idea
import ovh.vicart.ideas.repositories.NetworkRepository.api
import ovh.vicart.ideas.repositories.NetworkRepository.dataStore
import ovh.vicart.ideas.repositories.api.IdeasAPI
import ovh.vicart.ideas.repositories.database.AppDatabase
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

object IdeasRepository {

    private val Context.db : AppDatabase by createDatabase()

    suspend fun getIdeas(context: Context) : LiveData<List<Idea>> {
        val ideas = mutableListOf<Idea>()
        if(context.dataStore.data.count { it.contains(NetworkRepository.PreferenceKeys.tokenKey) } > 0) {
            val apiIdeas = api.getIdeas(context.dataStore.data.map { it[NetworkRepository.PreferenceKeys.tokenKey] }.first()!!)
            ideas.addAll(apiIdeas)
            saveIdeas(context, apiIdeas)
        }
        if(context.db.ideaDao().countAll() > 0) {
            for(idea in context.db.ideaDao().getAll()) {
                if(!ideas.map { it.id }.contains(idea.id)) {
                    ideas.add(idea)
                }
            }
        }
        return liveData {
            emit(ideas)
        }
    }

    private fun saveIdeas(context: Context, ideas: List<Idea>) {
        context.db.ideaDao().insertIdeas(*ideas.toTypedArray())
    }

    fun clearRepositories(context: Context) {
        context.db.ideaDao().deleteAll()
    }

    private fun createDatabase() : ReadOnlyProperty<Context, AppDatabase> {
        return ReadOnlyProperty { thisRef, _ ->
            Room.databaseBuilder(thisRef, AppDatabase::class.java, "idea-database").build()
        }
    }
}