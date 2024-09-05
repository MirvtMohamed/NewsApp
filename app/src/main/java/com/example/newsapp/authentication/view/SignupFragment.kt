package com.example.newsapp.authentication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.authentication.data.AuthRepository
import com.example.newsapp.authentication.viewmodel.AuthViewModel
import com.example.newsapp.authentication.viewmodel.AuthViewModelFactory
import com.example.newsapp.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root

        val firebaseAuth = FirebaseAuth.getInstance()
        val authRepository = AuthRepository(firebaseAuth)
        val authViewModelFactory = AuthViewModelFactory(authRepository)
        authViewModel = ViewModelProvider(this, authViewModelFactory)[AuthViewModel::class.java]

        binding.signupButton.setOnClickListener {
            val email = binding.signupEmail.text.toString().trim()
            val password = binding.signpPassword.text.toString().trim()

            authViewModel.signUp(email, password).observe(viewLifecycleOwner) { result ->
                if (result.isSuccess) {
                    // Handle successful sign-up (navigate to another screen)
                } else {
                    // Handle sign-up failure (show error message)
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
