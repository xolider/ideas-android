package ovh.vicart.ideas.viewmodels

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ovh.vicart.ideas.IdeasApp
import ovh.vicart.ideas.models.Idea
import ovh.vicart.ideas.repositories.IdeasRepository
import ovh.vicart.ideas.util.Utils

class MainViewModel(app: Application) : AndroidViewModel(app) {

    var connected = ObservableBoolean(false)

    val ideas: LiveData<List<Idea>> = liveData {
        emitSource(IdeasRepository.getIdeas(app.applicationContext))
    }

    init {

    }
}