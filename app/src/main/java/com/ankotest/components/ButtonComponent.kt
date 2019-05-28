package com.ankotest.components

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import com.ankotest.response.Component
import com.ankotest.utils.PerformAction
import com.ankotest.utils.loadBackground
import org.jetbrains.anko.*

/**
 * Created by Mukhamed Issa on 5/8/19.
 */
class ButtonComponent(
        private val component: Component,
        private val performAction: PerformAction): AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        button(component.text) {
            component.style.backgroundColor?.let {
                backgroundColor = Color.parseColor(it)
            }

            component.style.backgroundResource?.let {
                loadBackground(it)
            }

            component.style.textColor?.let {
                textColor = Color.parseColor(it)
            }

            component.action?.let { action ->
                setOnClickListener { performAction.invoke(action) }
            }

            textSize = component.style.textSize.toFloat()

            layoutParams = LinearLayout.LayoutParams(component.params.getWidth(),
                    component.params.getHeight(), component.params.weight.toFloat()).apply {
                gravity = component.params.getLayoutGravity()

                component.params.padding?.let {
                    setPadding(dip(it.left), dip(it.top), dip(it.right), dip(it.bottom))
                }

                component.params.margin?.let {
                    setMargins(dip(it.left), dip(it.top), dip(it.right), dip(it.bottom))
                }
            }
        }
    }
}