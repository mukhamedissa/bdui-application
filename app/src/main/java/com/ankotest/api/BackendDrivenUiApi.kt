package com.ankotest.api

import com.ankotest.response.ServiceResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Mukhamed Issa on 5/7/19.
 */
interface BackendDrivenUiApi {

    @GET("/")
    fun getResponse(): Deferred<Response<ServiceResponse>>
}