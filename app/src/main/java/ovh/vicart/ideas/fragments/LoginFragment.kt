package ovh.vicart.ideas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ovh.vicart.ideas.databinding.FragmentLoginBinding
import ovh.vicart.ideas.viewmodels.AuthViewModel
import ovh.vicart.ideas.viewmodels.MainViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val vm: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.vm = vm
        return binding.root
    }
}