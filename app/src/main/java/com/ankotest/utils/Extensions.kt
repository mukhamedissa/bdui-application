package com.ankotest.utils

import android.content.Context
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

/**
 * Created by Mukhamed Issa on 5/6/19.
 */
fun <T: AnkoComponent<Context>> ViewGroup.mount(component: T, onMount: (T.() -> Unit)? = null) =
        context.render(component as AnkoComponent<Context>).apply {
            addView(this)
            onMount?.invoke(component)
        }

inline fun Context.render(component: AnkoComponent<Context>) =
        component.createView(AnkoContext.createReusable(this))