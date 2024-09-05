package com.example.newsapp.authentication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentSplashScreenBinding
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        // Simulate a delay for splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("SplashScreenFragment", "Navigating based on user state")
            refreshUser()
        }, 10000) // 2 seconds delay

        return view
    }

    private fun refreshUser() {
        val firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        if (currentUser == null) {
            Log.d("SplashScreen", "No user logged in")
            // Navigate to login screen
            findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            return
        }

        Log.d("SplashScreen", "Current user: $currentUser")
        currentUser.reload().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("SplashScreen", "User reload successful")
                if (currentUser.isEmailVerified) {
                    Log.d("SplashScreen", "User is email verified")
                    findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
                } else {
                    Log.d("SplashScreen", "User email not verified")
                    findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
                }
            } else {
                Log.d("SplashScreen", "User reload failed: ${task.exception?.message}")
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
