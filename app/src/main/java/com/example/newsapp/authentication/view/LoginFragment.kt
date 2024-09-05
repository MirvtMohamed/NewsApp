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
import com.example.newsapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        // Initialize Firebase Auth and ViewModel
        val firebaseAuth = FirebaseAuth.getInstance()
        val authRepository = AuthRepository(firebaseAuth)
        val authViewModelFactory = AuthViewModelFactory(authRepository)
        authViewModel = ViewModelProvider(this, authViewModelFactory)[AuthViewModel::class.java]

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmail.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()

            // Validate input before attempting to log in
            if (email.isEmpty()) {
                Toast.makeText(context, "Please enter an email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(context, "Please enter a password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Proceed with login if inputs are valid
            authViewModel.login(email, password).observe(viewLifecycleOwner) { result ->
                if (result.isSuccess) {
                    val user = result.getOrNull()
                    handleLoginSuccess(user)
                } else {
                    Toast.makeText(context, "Login failed: ${result.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.forgotPassword.setOnClickListener {
            // Navigate to the "Forgot Password" screen if implemented
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.signupRedirectText.setOnClickListener {
            // Navigate to SignUpFragment
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        return view
    }

    private fun handleLoginSuccess(user: FirebaseUser?) {
        if (user != null) {
            if (user.isEmailVerified) {
                // Navigate to home screen if email is verified
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                // Prompt the user to verify their email
                Toast.makeText(context, "Please verify your email before logging in.", Toast.LENGTH_LONG).show()
                authViewModel.sendEmailVerification().observe(viewLifecycleOwner) { verificationResult ->
                    if (verificationResult.isSuccess) {
                        Toast.makeText(context, "Verification email sent. Please check your inbox.", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Failed to send verification email: ${verificationResult.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
