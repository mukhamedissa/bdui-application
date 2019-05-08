package com.ankotest.components

import com.ankotest.response.Component
import com.ankotest.response.ComponentConstants
import com.ankotest.utils.PerformAction
import java.lang.IllegalArgumentException

/**
 * Created by Mukhamed Issa on 5/6/19.
 */
object ComponentFactory {

    fun getViewComponent(
            component: Component,
            performAction: PerformAction) = when(component.type) {
        ComponentConstants.TEXT_COMPONENT -> TextComponent(component)
        ComponentConstants.INPUT_COMPONENT -> InputComponent(component)
        ComponentConstants.LINEAR_COMPONENT -> LinearComponent(component, performAction)
        ComponentConstants.VIEW_COMPONENT -> ViewComponent(component)
        ComponentConstants.BUTTON_COMPONENT -> ButtonComponent(component, performAction)
        ComponentConstants.IMAGE_COMPONENT -> ImageComponent(component)
        else -> throw IllegalArgumentException("Invalid component: ${component.type}")
    }
}