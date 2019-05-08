package com.ankotest.components

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.ankotest.response.Component
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.dip
import org.jetbrains.anko.imageView

/**
 * Created by Mukhamed Issa on 5/8/19.
 */
class ImageComponent(private val component: Component) : AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>) = with(ui) {
        imageView {
            Picasso.get()
                    .load(component.url)
                    .into(this)

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