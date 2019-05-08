package com.ankotest.api.repo

import android.util.Log
import com.ankotest.api.BackendDrivenUiApi
import com.ankotest.response.Component

/**
 * Created by Mukhamed Issa on 5/7/19.
 */
class ServiceRepository(private val api: BackendDrivenUiApi): BaseRepository() {

    suspend fun getResponse(): List<Component>? {
        val response = safeApiCall(
                call = {
                    api.getResponse().await()
                },
                errorMessage = "Error"
        )

        return response?.containers
    }

}