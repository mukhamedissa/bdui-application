package com.ankotest.components

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ankotest.response.Component
import com.ankotest.utils.loadBackground
import org.jetbrains.anko.*

/**
 * Created by Mukhamed Issa on 5/6/19.
 */
class InputComponent(private val component: Component): AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        editText {
            hint = component.hint

            textSize = component.style.textSize.toFloat()
            component.style.textColor?.let {
                textColor = Color.parseColor(it)
            }

            component.style.backgroundColor?.let {
                backgroundColor = Color.parseColor(it)
            }

            component.style.backgroundResource?.let {
                loadBackground(it)
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