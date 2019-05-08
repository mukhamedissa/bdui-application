package com.ankotest.components

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ankotest.response.Component
import org.jetbrains.anko.*

/**
 * Created by Mukhamed Issa on 5/6/19.
 */
class TextComponent(private val component: Component): AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        textView(text = component.text) {
            textSize = component.style.textSize.toFloat()
            component.style.textColor?.let {
                textColor = Color.parseColor(it)
            }

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