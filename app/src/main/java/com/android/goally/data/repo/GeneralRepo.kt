package com.android.goally.data.repo

import com.android.goally.constants.WebServiceConstant
import com.android.goally.data.db.dao.GeneralDao
import com.android.goally.data.db.entities.token.Authentication
import com.android.goally.data.model.api.ErrorResponse
import com.android.goally.data.model.api.response.reminder.ReminderResponse
import com.android.goally.data.network.rest.api.GeneralApi
import com.haroldadmin.cnradapter.NetworkResponse
import java.util.concurrent.Flow



class GeneralRepo(
    private val generalApi: GeneralApi,
    private val generalDao: GeneralDao,
) {

    suspend fun checkHealth() = generalApi.checkHealth()
    suspend fun getToken(userEmail: String) = generalApi.getToken(userEmail)

    suspend fun getReminders() = generalApi.getReminders(WebServiceConstant.Token)

    fun getAuthenticationLive() = generalDao.getAuthenticationLive()
    suspend fun getAuthentication() = generalDao.getAuthentication()


}