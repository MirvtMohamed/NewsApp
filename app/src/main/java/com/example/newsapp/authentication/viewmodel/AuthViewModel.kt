package com.example.newsapp.authentication.viewmodel



import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.authentication.data.AuthRepository
import com.google.firebase.auth.FirebaseUser

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun login(email: String, password: String): LiveData<Result<FirebaseUser?>> {
        return authRepository.login(email, password)
    }

    fun signUp(email: String, password: String): LiveData<Result<FirebaseUser?>> {
        return authRepository.signUp(email, password)
    }

    fun sendEmailVerification(): LiveData<Result<Boolean>> {
        return authRepository.sendEmailVerification()
    }

    fun resetPassword(email: String): LiveData<Result<Boolean>> {
        return authRepository.resetPassword(email)
    }
}
