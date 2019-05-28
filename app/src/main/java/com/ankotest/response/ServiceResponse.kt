package com.ankotest.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Mukhamed Issa on 5/6/19.
 */
data class ServiceResponse(
        @SerializedName("code") val code: Int,
        @SerializedName("containers") val containers: List<Component>)

data class Component(
        @SerializedName("id") val id: Int = -1,
        @SerializedName("type") val type: String,
        @SerializedName("text") val text: String? = null,
        @SerializedName("hint") val hint: String? = null,
        @SerializedName("image_url") val url: String? = null,
        @SerializedName("params") val params: ComponentParams = ComponentParams(),
        @SerializedName("style") val style: ComponentStyle = ComponentStyle(),
        @SerializedName("action") val action: ComponentAction? = null,
        @SerializedName("children") val children: ArrayList<Component>? = null)

data class ComponentStyle(
        @SerializedName("background_color") val backgroundColor: String? = null,
        @SerializedName("background_resource") val backgroundResource: String? = null,
        @SerializedName("text_color") val textColor: String? = null,
        @SerializedName("text_size") val textSize: Int = ComponentConstants.DEFAULT_TEXT_SIZE)

data class ComponentParams(
        @SerializedName("width") val width: String = WRAP_CONTENT,
        @SerializedName("height") val height: String = WRAP_CONTENT,
        @SerializedName("weight") val weight: Int = 0,
        @SerializedName("padding") val padding: ComponentIndent? = null,
        @SerializedName("margin") val margin: ComponentIndent? = null,
        @SerializedName("layout_gravity") val layoutGravity: String? = null,
        @SerializedName("orientation") val orientation: String? = ORIENTATION_VERTICAL) {

    companion object {
        const val WRAP_CONTENT = "wrap_content"
        const val MATCH_PARENT = "match_parent"

        const val ORIENTATION_VERTICAL = "vertical"
        const val ORIENTATION_HORIZONTAL = "horizontal"

        const val CENTER_VERTICAL = "center_vertical"
        const val CENTER_HORIZONTAL = "center_horizontal"
    }

    fun getWidth() = when(width) {
        WRAP_CONTENT -> -2
        MATCH_PARENT -> -1
        else -> width.toInt()
    }

    fun getHeight() = when(height) {
        WRAP_CONTENT -> -2
        MATCH_PARENT -> -1
        else -> height.toInt()
    }

    fun getLayoutGravity() = when(layoutGravity) {
        CENTER_VERTICAL -> 16
        CENTER_HORIZONTAL -> 1
        else -> 0
    }

    fun getOrientation() = when(orientation) {
        ORIENTATION_VERTICAL -> 1
        ORIENTATION_HORIZONTAL -> 0
        else -> 1
    }
}

data class ComponentIndent(
        @SerializedName("left") val left: Int = 0,
        @SerializedName("right") val right: Int = 0,
        @SerializedName("top") val top: Int = 0,
        @SerializedName("bottom") val bottom: Int = 0) {

    constructor(vertical: Int = 0, horizontal: Int = 0):
            this(top = vertical, bottom = vertical,
                    left = horizontal, right = horizontal)

    constructor(all: Int = 0):
            this(top = all, bottom = all,
                    left = all, right = all)
}

data class ComponentAction(
        @SerializedName("action_type") val type: String? = null,
        @SerializedName("uri") val uri: String? = null,
        @SerializedName("data") val data: Map<String, String>? = null) {

    companion object {
        const val OPEN_URL = "open_url"
        const val OPEN_ACTIVITY = "open_activity"
    }
}


