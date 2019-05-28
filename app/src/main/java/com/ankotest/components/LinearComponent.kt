package com.ankotest.components

import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import com.ankotest.response.Component
import com.ankotest.utils.PerformAction
import com.ankotest.utils.mount
import org.jetbrains.anko.*

/**
 * Created by Mukhamed Issa on 5/6/19.
 */
class LinearComponent(
        private val component: Component,
        private val performAction: PerformAction): AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        val layout = linearLayout {
            orientation = component.params.getOrientation()

            component.style.backgroundColor?.let {
                backgroundColor = Color.parseColor(it)
            }

            layoutParams = LinearLayout.LayoutParams(component.params.getWidth(),
                   component.params.getHeight(), component.params.weight.toFloat()).apply {
                component.params.padding?.let {
                    setPadding(dip(it.left), dip(it.top), dip(it.right), dip(it.bottom))
                }

                component.params.margin?.let {
                    setMargins(dip(it.left), dip(it.top), dip(it.right), dip(it.bottom))
                }
           }
        }

        component.children?.let {
            for (child in it) {
                layout.mount(ViewComponentFactory.getViewComponent(child, performAction))
            }
        }


        return@with layout
    }

}