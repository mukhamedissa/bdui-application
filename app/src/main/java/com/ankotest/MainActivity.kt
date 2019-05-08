package com.ankotest

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.ankotest.api.ServiceFactory
import com.ankotest.api.viewmodel.ServiceViewModel
import com.ankotest.components.ComponentFactory
import com.ankotest.response.Component
import com.ankotest.utils.ActionHandler
import com.ankotest.utils.PerformAction
import com.ankotest.utils.mount
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val serviceViewModel by lazy { ViewModelProviders.of(this)
            .get(ServiceViewModel::class.java) }

    private val performAction: PerformAction = {
        startActivity(ActionHandler.getIntentForAction(it))
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
            container.mount(ComponentFactory.getViewComponent(it, performAction))
        }
    }
}
