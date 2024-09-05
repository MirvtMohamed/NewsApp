package com.example.newsapp.authentication.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository(private val firebaseAuth: FirebaseAuth) {

    fun login(email: String, password: String): LiveData<Result<FirebaseUser?>> {
        val resultLiveData = MutableLiveData<Result<FirebaseUser?>>()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    resultLiveData.postValue(Result.success(firebaseAuth.currentUser))
                } else {
                    resultLiveData.postValue(Result.failure(task.exception!!))
                }
            }
        return resultLiveData
    }

    fun signUp(email: String, password: String): LiveData<Result<FirebaseUser?>> {
        val resultLiveData = MutableLiveData<Result<FirebaseUser?>>()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    resultLiveData.postValue(Result.success(firebaseAuth.currentUser))
                } else {
                    resultLiveData.postValue(Result.failure(task.exception!!))
                }
            }
        return resultLiveData
    }

    fun sendEmailVerification(): LiveData<Result<Boolean>> {
        val resultLiveData = MutableLiveData<Result<Boolean>>()

        firebaseAuth.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    resultLiveData.postValue(Result.success(true))
                } else {
                    resultLiveData.postValue(Result.failure(task.exception!!))
                }
            }
        return resultLiveData
    }

    fun resetPassword(email: String): LiveData<Result<Boolean>> {
        val resultLiveData = MutableLiveData<Result<Boolean>>()

        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    resultLiveData.postValue(Result.success(true))
                } else {
                    resultLiveData.postValue(Result.failure(task.exception!!))
                }
            }
        return resultLiveData
    }
}
