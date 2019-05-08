package com.ankotest.components

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import com.ankotest.response.Component
import org.jetbrains.anko.*

/**
 * Created by Mukhamed Issa on 5/8/19.
 */
class ViewComponent(private val component: Component): AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        view {
            component.style.backgroundColor?.let {
                backgroundColor = Color.parseColor(it)
            }

            layoutParams = with(component.params) {
                LinearLayout.LayoutParams(
                        getWidth(),
                        getHeight(),
                        weight.toFloat())
            }
        }
    }

}