package com.example.githubsearchrepo.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearchrepo.R

class StickyHeaderDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val dividerColor = Color.GRAY
    private val dividerHeight = 2.dpToPx(context) // 自定義分隔線的高度，這裡假設為 2dp

    private fun Int.dpToPx(context: Context): Int {
        val scale = context.resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }

    private val headerView: View = LayoutInflater.from(context).inflate(R.layout.header_view, null)


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        // 計算分隔線的左側和右側邊界
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        headerView.measure(
            View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)
        )


        // 迭代 RecyclerView 的每個子項目，為每個子項目繪製分隔線
        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            headerView.layout(0, 0 , headerView.measuredWidth, headerView.measuredHeight)
            if (i != 0) {
                // 計算分隔線的上側和下側邊界
                val top = child.bottom.toFloat()
                val bottom = top + dividerHeight
                // 繪製分隔線
                c.drawRect(left.toFloat(), top , right.toFloat(), bottom, dividerPaint)
            } else {
                // 绘制 Header 的内容
                c.save()
                c.translate(0f, 0f) // 設置 Header 的位置，固定在最上方
                headerView.draw(c)
                c.restore()
            }
        }
    }

    private val dividerPaint = Paint().apply {
        color = dividerColor
    }

}