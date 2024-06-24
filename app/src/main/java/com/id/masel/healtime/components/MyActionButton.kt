package com.id.masel.healtime.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.id.masel.healtime.R

class MyActionButton : AppCompatButton {
    private var enabledBackground: Drawable? = null
    private var disabledBackground: Drawable? = null
    private var txtColorEnable: Int = 0
    private var txtColorDisable: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = if(isEnabled){
            setTextColor(txtColorEnable)
            enabledBackground
        } else {
            setTextColor(txtColorDisable)
            disabledBackground
        }

        textSize = 14f
        gravity = Gravity.CENTER
        text = if(isEnabled) context.getString(R.string.submit) else context.getString(R.string.isi_dulu)
    }

    private fun init() {
        txtColorEnable = ContextCompat.getColor(context, R.color.blue_700)
        txtColorDisable = ContextCompat.getColor(context, R.color.blue_700)
        enabledBackground = ContextCompat.getDrawable(context, R.drawable.action_button) as Drawable
        disabledBackground = ContextCompat.getDrawable(context, R.drawable.action_button_disabled) as Drawable
    }
}