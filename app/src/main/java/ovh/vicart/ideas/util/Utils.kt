package ovh.vicart.ideas.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.math.BigInteger
import java.security.MessageDigest

object Utils {

    val API_BASE_URL = "http://localhost:8080"

    fun isConnected(context: Context) : Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
                return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                val networks = cm.allNetworks
                for(n in networks) {
                    val nInfo = cm.getNetworkInfo(n)
                    if(nInfo != null && nInfo.isConnected) return true
                }
            }
            else -> {
                val networks = cm.allNetworkInfo
                for(nInfo in networks) {
                    if(nInfo != null && nInfo.isConnected) return true
                }
            }
        }
        return false
    }

    fun hash(str: String) : String {
        val md = MessageDigest.getInstance("SHA-512")
        val digest = md.digest(str.toByteArray())
        val no = BigInteger(1, digest)
        var hashtext = no.toString(16)
        while(hashtext.length < 128) {
            hashtext = "0$hashtext"
        }
        return hashtext
    }
}