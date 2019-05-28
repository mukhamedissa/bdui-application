package com.ankotest.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.ankotest.response.ComponentAction

/**
 * Created by Mukhamed Issa on 5/8/19.
 */

typealias PerformAction = (ComponentAction) -> Unit

object ActionHandler {

    fun getIntentForAction(context: Context, action: ComponentAction) = when(action.type) {
        ComponentAction.OPEN_URL -> Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(action.uri)
        }

        ComponentAction.OPEN_ACTIVITY -> {
            val activity = Class.forName(action.uri)

            Intent(context, activity).apply {
                action.data?.forEach { (key, value) ->
                    putExtra(key, value)
                }
            }
        }

        else -> throw IllegalArgumentException("Unknown action ${action.type}")
    }
}

