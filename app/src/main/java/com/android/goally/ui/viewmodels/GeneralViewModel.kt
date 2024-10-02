package com.android.goally.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.goally.data.db.entities.token.Authentication
import com.android.goally.data.model.api.ErrorResponse
import com.android.goally.data.model.api.response.reminder.ReminderResponse
import com.android.goally.data.repo.GeneralRepo
import com.android.goally.util.LogUtil
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val generalRepo: GeneralRepo
) : ViewModel() {

    fun checkServerHealth(
        onLoading: (Boolean) -> Unit,
        onError: (String) -> Unit,
        onSuccess: (String) -> Unit
    ) {
        onLoading(true)
        viewModelScope.launch {
            when (val res = generalRepo.checkHealth()) {
                is NetworkResponse.Success -> {
                    LogUtil.i(res.body.toString())
                    if (res.body?.status.equals("ok", true)) {
                        onSuccess("Server is up")
                    } else {
                        onError("Server is down")
                    }
                    onLoading(false)
                }

                is NetworkResponse.ServerError -> {
                    LogUtil.e(res.code.toString())
                    LogUtil.e(res.body?.message)
                    onError(res.body?.message ?: "Server error")
                    onLoading(false)
                }

                is NetworkResponse.NetworkError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Network error")
                    onLoading(false)
                }

                is NetworkResponse.UnknownError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Unknown error")
                    onLoading(false)
                }
            }
        }
    }

    fun getTokenFor(
        userEmail: String,
        onLoading: (Boolean) -> Unit,
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) {
        onLoading(true)
        viewModelScope.launch {
            when (val res = generalRepo.getToken(userEmail)) {
                is NetworkResponse.Success -> {
                    LogUtil.i(res.body.toString())
                    res.body?.let {
                        if (!it.token.isNullOrEmpty() && !it.name.isNullOrEmpty()) {
                            //save token here which will be used for further api calls
                            onSuccess()
                        }
                    } ?: run {
                        onError("Something went wrong")
                    }
                    onLoading(false)
                }

                is NetworkResponse.ServerError -> {
                    LogUtil.e(res.code.toString())
                    LogUtil.e(res.body?.message)
                    onError(res.body?.message ?: "Server error")
                    onLoading(false)
                }

                is NetworkResponse.NetworkError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Network error")
                    onLoading(false)
                }

                is NetworkResponse.UnknownError -> {
                    res.error.printStackTrace()
                    onError(res.error.message ?: "Unknown error")
                    onLoading(false)
                }
            }
        }
    }


    // Get Reminder
    private val _reminderState =
        MutableStateFlow<NetworkResponse<ReminderResponse, ErrorResponse>?>(null)
    val reminderState: StateFlow<NetworkResponse<ReminderResponse, ErrorResponse>?> = _reminderState


//    fun getReminders(
//        onLoading: (Boolean) -> Unit,
//        onError: (String) -> Unit,
//        onSuccess: () -> Unit
//    ) {
//        onLoading(true)
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                when (val response = generalRepo.getReminders()) {
//                    is NetworkResponse.Success -> {
//                        response.body?.let {
//                            if (it.routines.isNullOrEmpty()) {
//                                _reminderState.value = response
//                            } else {
//                                onSuccess()
//                            }
//                        } ?: run {
//                            onError("Something went wrong")
//                        }
//                        onLoading(false)
//                    }
//
//                    is NetworkResponse.ServerError -> {
//                        onError(response.body?.message ?: "Server error")
//                        onLoading(false)
//                    }
//
//                    is NetworkResponse.NetworkError -> {
//                        response.error.printStackTrace()
//                        onError(response.error.message ?: "Network error")
//                        onLoading(false)
//                    }
//
//                    is NetworkResponse.UnknownError -> {
//                        response.error.printStackTrace()
//                        onError(response.error.message ?: "Unknown error")
//                        onLoading(false)
//                    }
//                }
//            }
//        }
//    }


    fun getReminderList() {
        viewModelScope.launch {
            try {
                val response = generalRepo.getReminders()
                Log.d("API Response", response.toString())
                _reminderState.value = response
            } catch (e: Exception) {
                Log.d("Error Message", e.message.toString())
            }
        }
    }

    fun getAuthenticationLive() = generalRepo.getAuthenticationLive()
    suspend fun getAuthentication() = generalRepo.getAuthentication()
}