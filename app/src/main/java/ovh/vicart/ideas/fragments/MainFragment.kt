package ovh.vicart.ideas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.snackbar.Snackbar
import ovh.vicart.ideas.R
import ovh.vicart.ideas.databinding.FragmentMainBinding
import ovh.vicart.ideas.util.Utils
import ovh.vicart.ideas.viewmodels.MainViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by navGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.vm = mainViewModel

        binding.mainLoginBtn.setOnClickListener {
            val frag = LoginFragment()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction?.add(android.R.id.content, frag)?.addToBackStack(null)?.commit()
        }

        if(!Utils.isConnected(requireContext())) {
            val snack = Snackbar.make(binding.root, R.string.internet_not_connected, Snackbar.LENGTH_LONG)
            snack.setAction(R.string.close) {
                snack.dismiss()
            }
            snack.show()
        }

        return binding.root
    }
}