package com.ankotest.api.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.ankotest.api.ServiceFactory
import com.ankotest.api.repo.ServiceRepository
import com.ankotest.response.Component
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Mukhamed Issa on 5/7/19.
 */
class ServiceViewModel: ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository = ServiceRepository(ServiceFactory.serviceApi)

    val responseLiveData = MutableLiveData<List<Component>>()

    fun loadResponse() {
        scope.launch {
            val response = repository.getResponse()
            responseLiveData.postValue(response)
        }
    }

}