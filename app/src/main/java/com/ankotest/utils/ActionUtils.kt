package com.ankotest.utils

import android.content.Intent
import android.net.Uri
import com.ankotest.response.ComponentAction

/**
 * Created by Mukhamed Issa on 5/8/19.
 */

typealias PerformAction = (ComponentAction) -> Unit

object ActionHandler {

    fun getIntentForAction(action: ComponentAction) = when(action.type) {
        ComponentAction.OPEN_URL -> Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(action.uri)
        }
        else -> throw IllegalArgumentException("Unknown action ${action.type}")
    }
}