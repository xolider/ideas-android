package ovh.vicart.ideas.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.TypedArrayUtils
import androidx.core.view.updateLayoutParams
import ovh.vicart.ideas.R
import kotlin.math.min

class StatusView : View {

    private var _isActive: Boolean = false

    private val paint = Paint()

    var isActive
        get() = _isActive
        set(value) {
            _isActive = value
            invalidate()
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.StatusView, defStyle, 0)
        _isActive = a.getBoolean(R.styleable.StatusView_active, _isActive)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWith = suggestedMinimumWidth + paddingLeft + paddingRight
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        setMeasuredDimension(measureDim(desiredWith, widthMeasureSpec), measureDim(desiredHeight, heightMeasureSpec))
    }

    private fun measureDim(desiredSize: Int, measureSpec: Int) : Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        if(specMode == MeasureSpec.EXACTLY) {
            result = specSize
        }
        else {
            result = desiredSize
            if(specMode == MeasureSpec.AT_MOST) {
                result = min(result, specSize)
            }
        }
        return result
    }

    override fun onDraw(canvas: Canvas?) {
        paint.color = if(_isActive) Color.GREEN else Color.RED
        paint.isAntiAlias = true
        val centerX = (measuredWidth/2).toFloat()
        val centerY = (measuredHeight/2).toFloat()
        canvas?.drawCircle(centerX, centerY, measuredWidth.toFloat()-centerX-5, paint)
        super.onDraw(canvas)
    }
}