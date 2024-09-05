package com.example.newsapp.authentication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
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

        // Handle sign-up button click
        binding.signupButton.setOnClickListener {
            val email = binding.signupEmail.text.toString().trim()
            val password = binding.signpPassword.text.toString().trim()

            // Validate non-empty fields
            if (email.isEmpty()) {
                binding.signupEmail.error = "Email cannot be empty"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.signpPassword.error = "Password cannot be empty"
                return@setOnClickListener
            }

            // Proceed with sign-up
            authViewModel.signUp(email, password).observe(viewLifecycleOwner) { result ->
                if (result.isSuccess) {
                    // Trigger email verification
                    authViewModel.sendEmailVerification().observe(viewLifecycleOwner) { verificationResult ->
                        if (verificationResult.isSuccess) {
                            Toast.makeText(context, "Verification email sent. Please check your inbox.", Toast.LENGTH_LONG).show()
                            // Navigate to login screen
                            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                        } else {
                            Toast.makeText(context, "Failed to send verification email. Please try again.", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Sign-up failed: ${result.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

        // Handle login redirect text click
        binding.loginRedirectText.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
