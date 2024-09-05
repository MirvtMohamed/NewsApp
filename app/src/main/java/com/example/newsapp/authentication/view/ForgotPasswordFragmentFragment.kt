package com.example.newsapp.authentication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.authentication.data.AuthRepository
import com.example.newsapp.authentication.viewmodel.AuthViewModel
import com.example.newsapp.authentication.viewmodel.AuthViewModelFactory
import com.example.newsapp.databinding.FragmentForgotPasswordFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        val firebaseAuth = FirebaseAuth.getInstance()
        val authRepository = AuthRepository(firebaseAuth)
        val authViewModelFactory = AuthViewModelFactory(authRepository)
        authViewModel = ViewModelProvider(this, authViewModelFactory)[AuthViewModel::class.java]

        binding.resetButton.setOnClickListener {
            val email = binding.resetEmail.text.toString().trim()

            if (email.isNotEmpty()) {
                authViewModel.resetPassword(email).observe(viewLifecycleOwner) { result ->
                    if (result.isSuccess) {
                        // Notify the user that reset email was sent
                        Toast.makeText(context, "Password reset email sent", Toast.LENGTH_SHORT).show()
                    } else {
                        // Notify the user of an error
                        Toast.makeText(context, "Failed to send reset email", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
