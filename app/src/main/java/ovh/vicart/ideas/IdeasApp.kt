package ovh.vicart.ideas

import android.app.Application
import com.google.android.material.color.DynamicColors

class IdeasApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}