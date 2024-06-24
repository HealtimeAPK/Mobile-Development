package com.id.masel.healtime.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.id.masel.healtime.R

class MyEditText: AppCompatEditText, View.OnTouchListener {
    private lateinit var mClearIconButtonImage: Drawable
    private val mInputType = this.inputType

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(context: Context) : super(context) {
        init()
    }

    @RequiresApi(Build.VERSION_CODES.O)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    @RequiresApi(Build.VERSION_CODES.O)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    @RequiresApi(Build.VERSION_CODES.O)

    private fun init() {
        mClearIconButtonImage =
            ContextCompat.getDrawable(context, R.drawable.ic_baseline_close_24) as Drawable
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) showClearButton() else hideClearButton()
            }

            override fun afterTextChanged(s: Editable) {
                val p0 = s.toString()

                when(mInputType){
                    EMAIL_TYPE -> {
                        if(s.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(p0).matches()){
                            error = context.getString(R.string.email_invalid)
                        }
                    }
                    PASS_TYPE -> {
                        if(s.length in 1..7){
                            error = context.getString(R.string.password_invalid)
                        }
                    }
                }
            }
        })
    }

    private fun showClearButton() {
        setButtonDrawables(endOfText = mClearIconButtonImage)
    }

    private fun hideClearButton() {
        setButtonDrawables()
    }

    private fun setButtonDrawables(
        startOfText: Drawable? = null,
        topOfText: Drawable? = null,
        endOfText: Drawable? = null,
        bottomOfText: Drawable? = null
    ){
        setCompoundDrawablesWithIntrinsicBounds(
            startOfText,
            topOfText,
            endOfText,
            bottomOfText
        )
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val toggleButtonStart: Float
            val toggleButtonEnd: Float
            var isToggleButtonClicked = false
            when (layoutDirection) {
                View.LAYOUT_DIRECTION_RTL -> {
                    toggleButtonEnd =
                        (mClearIconButtonImage.intrinsicWidth + paddingStart).toFloat()
                    when {
                        event.x < toggleButtonEnd -> isToggleButtonClicked = true
                    }
                }
                else -> {
                    toggleButtonStart =
                        (width - paddingEnd - mClearIconButtonImage.intrinsicWidth).toFloat()
                    when {
                        event.x > toggleButtonStart -> isToggleButtonClicked = true
                    }
                }
            }
            return if (isToggleButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        mClearIconButtonImage = ContextCompat.getDrawable(context,
                            R.drawable.ic_baseline_close_24
                        ) as Drawable
                        showClearButton()
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        mClearIconButtonImage = ContextCompat.getDrawable(context,
                            R.drawable.ic_baseline_close_24
                        ) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideClearButton()
                        return true
                    }
                    else -> false
                }
            } else false

        }
        return false
    }

    companion object{
        const val EMAIL_TYPE = 0x00000021
        const val PASS_TYPE = 0x00000081
    }
}