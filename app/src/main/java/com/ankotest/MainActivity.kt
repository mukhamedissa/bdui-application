package com.ankotest

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ankotest.api.viewmodel.ServiceViewModel
import com.ankotest.components.ViewComponentFactory
import com.ankotest.response.Component
import com.ankotest.utils.ActionHandler
import com.ankotest.utils.PerformAction
import com.ankotest.utils.mount
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val serviceViewModel by lazy { ViewModelProviders.of(this)
            .get(ServiceViewModel::class.java) }

    private val performAction: PerformAction = {
        startActivity(ActionHandler.getIntentForAction(this, it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceViewModel.loadResponse()

        serviceViewModel.responseLiveData.observe(this, Observer {
            it ?: return@Observer

            initUi(it)
        })


    }

    private fun initUi(components: List<Component>) {
        components.forEach {
            container.mount(ViewComponentFactory.getViewComponent(it, performAction))
        }
    }
}
