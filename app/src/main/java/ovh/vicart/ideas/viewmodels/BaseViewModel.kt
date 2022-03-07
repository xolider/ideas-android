package ovh.vicart.ideas.viewmodels

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(app: Application) : AndroidViewModel(app), Observable {

    private var callbacks: PropertyChangeRegistry? = null

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if(callbacks == null) {
                callbacks = PropertyChangeRegistry()
            }
        }
        callbacks?.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if(callbacks == null) return
        }
        callbacks?.remove(callback)
    }

    fun notifyChange() {
        synchronized(this) {
            if(callbacks == null) return
        }
        callbacks?.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if(callbacks == null) return
        }
        callbacks?.notifyCallbacks(this, fieldId, null)
    }
}