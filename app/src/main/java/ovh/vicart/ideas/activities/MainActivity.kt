package ovh.vicart.ideas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import ovh.vicart.ideas.R
import ovh.vicart.ideas.databinding.ActivityMainBinding
import ovh.vicart.ideas.util.Utils
import ovh.vicart.ideas.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.vm = vm

        binding.mainLoginBtn.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }

        setContentView(binding.root)

        if(!Utils.isConnected(this)) {
            val snack = Snackbar.make(binding.root, R.string.internet_not_connected, Snackbar.LENGTH_LONG)
            snack.setAction(R.string.close) {
                snack.dismiss()
            }
            snack.show()
        }
    }
}