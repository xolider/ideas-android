package ovh.vicart.ideas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import ovh.vicart.ideas.R
import ovh.vicart.ideas.databinding.FragmentLoginBinding
import ovh.vicart.ideas.viewmodels.AuthViewModel
import ovh.vicart.ideas.viewmodels.MainViewModel

class LoginFragment : DialogFragment() {

    private lateinit var binding: FragmentLoginBinding
    private val vm: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        binding.vm = vm

        binding.fragmentLoginCloseButton.setOnClickListener {
            dismiss()
        }

        return binding.root
    }
}