package ovh.vicart.ideas.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ovh.vicart.ideas.BR
import ovh.vicart.ideas.IdeasApp
import ovh.vicart.ideas.models.User
import ovh.vicart.ideas.repositories.UsersRepository
import ovh.vicart.ideas.util.Utils

class AuthViewModel(app: Application) : BaseViewModel(app) {

    val authLoading = ObservableBoolean(false)
    private val app = getApplication<IdeasApp>()

    private var username = ""
    private var password = ""

    fun loginClicked(view: View) {
        authLoading.set(true)
        viewModelScope.launch {
            withContext(this.coroutineContext) {
                UsersRepository.login(app.applicationContext, User(username, Utils.hash(password)))
            }
            authLoading.set(false)
        }
    }

    @Bindable
    fun getUsername(): String {
        return username
    }

    fun setUsername(value: String) {
        if(username != value) {
            username = value
            notifyPropertyChanged(BR.vm)
        }
    }

    @Bindable
    fun getPassword(): String {
        return password
    }

    fun setPassword(value: String) {
        if(password != value) {
            password = value
            notifyPropertyChanged(BR.vm)
        }
    }
}