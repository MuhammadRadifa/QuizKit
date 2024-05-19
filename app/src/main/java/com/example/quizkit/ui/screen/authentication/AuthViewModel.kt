package com.example.quizkit.ui.screen.authentication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizkit.data.network.local.room.UserEntity
import com.example.quizkit.data.network.remote.response.ResponseState
import com.example.quizkit.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
):ViewModel(){

    private val _loginThread = mutableStateOf(ResponseState<String>(loading = false))
    val loginThread: State<ResponseState<String>> = _loginThread

    private val _registerThread = mutableStateOf(ResponseState<String>(loading = false))
    val registerThread: State<ResponseState<String>> = _registerThread

    fun saveToken(token: String){
        viewModelScope.launch {
            authRepository.saveToken(token)
        }
    }

    fun login(email:String,password:String){
        viewModelScope.launch {
            _loginThread.value = _loginThread.value.copy(loading = true)
            try {
                authRepository.getUser(email).collect{
                        user ->
                    if(user.password == password){
                        _loginThread.value = _loginThread.value.copy(
                            loading = false,
                            data = "Login success",
                            error = null,
                        )
                    }else{
                        _loginThread.value = _loginThread.value.copy(
                            loading = false,
                            data = null,
                            error = "Password is incorrect",
                        )
                    }
                }
            }catch (e:Exception) {
                _loginThread.value = _loginThread.value.copy(
                    loading = false,
                    data = null,
                    error = e.message,
                )
            }
        }
    }

    fun register(userEntity: UserEntity){
        viewModelScope.launch {
            _registerThread.value = _registerThread.value.copy(loading = true)
            try {
                authRepository.register(userEntity)
                _registerThread.value = _registerThread.value.copy(
                    loading = false,
                    data = "Register success",
                    error = null,
                )
            }catch (e:Exception){
                _registerThread.value = _registerThread.value.copy(
                    loading = false,
                    data = null,
                    error = e.message,
                )
            }
        }
    }

//    fun updateAvatar(email: Int, background: Int, avatar: Int){
//        viewModelScope.launch {
//            try {
//                authRepository.updateAvatar(email, background, avatar)
//                loginThread.value = loginThread.value.copy(
//                    loading = false,
//                    data = null,
//                    error = null,
//                )
//            }catch (e:Exception){
//                loginThread.value = loginThread.value.copy(
//                    loading = false,
//                    data = null,
//                    error = e.message,
//                )
//            }
//        }
//    }


}