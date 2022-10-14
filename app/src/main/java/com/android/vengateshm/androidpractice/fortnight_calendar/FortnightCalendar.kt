package com.android.vengateshm.androidpractice.fortnight_calendar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.android.vengateshm.androidpractice.R
import com.android.vengateshm.androidpractice.databinding.FortnightCalendarBinding
import com.android.vengateshm.androidpractice.databinding.FortnightCalendarHeaderBinding
import com.android.vengateshm.androidpractice.databinding.FortnightCalendarWeekBinding
import java.util.*

class FortnightCalendar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = FortnightCalendarBinding.inflate(LayoutInflater.from(context), this, true)

    private var headerAdapter: HeaderAdapter? = null
    private var dayAdapter: DayAdapter? = null

    var startDay = Calendar.getInstance() // Current by default
    var onDaySelected: ((FortnightDay) -> Unit)? = null

    fun commit() {
        val fortnightDays = mutableListOf<FortnightDay>()
        val tempStartDay = startDay.clone() as Calendar
        fortnightDays.add(tempStartDay.toFortnightDay())
        repeat(13) {
            tempStartDay.addDay(1)
            fortnightDays.add(tempStartDay.toFortnightDay())
        }

        with(binding.headerGrid) {
            headerAdapter = headerAdapter ?: HeaderAdapter(fortnightDays.take(7))
            adapter = headerAdapter
        }

        with(binding.weekGrid) {
            dayAdapter = dayAdapter ?: DayAdapter(fortnightDays,
                onDaySelected = {
                    onDaySelected?.invoke(it)
                })
            adapter = dayAdapter
        }
    }
}

class HeaderAdapter(private val days: List<FortnightDay>) : BaseAdapter() {
    override fun getCount(): Int {
        return days.size
    }

    override fun getItem(position: Int): Any {
        return days[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rootView = convertView
            ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.fortnight_calendar_header, parent, false)
        with(FortnightCalendarHeaderBinding.bind(rootView)) {
            tvDayName.text = days[position].day
        }

        return rootView
    }
}

class DayAdapter(
    private val days: List<FortnightDay>,
    private val onDaySelected: ((FortnightDay) -> Unit)? = null,
) : BaseAdapter() {

    val DEFAULT_SELECTED_POSITION = 0

    private var selectedDayPos = DEFAULT_SELECTED_POSITION

    override fun getCount(): Int {
        return days.size
    }

    override fun getItem(position: Int): Any {
        return days[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rootView = convertView
            ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.fortnight_calendar_week, parent, false)
        with(FortnightCalendarWeekBinding.bind(rootView)) {
            tvDayName.setOnClickListener {
                onDaySelected?.invoke(days[position])
                selectedDayPos = position
                notifyDataSetChanged()
            }
            tvDayName.text = days[position].dayOfMonth.toString()
            if (selectedDayPos == position) {
                rootView.background =
                    ContextCompat.getDrawable(parent?.context!!, R.drawable.week_day_bg)
                tvDayName.setTextColor(Color.BLACK)
            } else {
                rootView.background = null
                tvDayName.setTextColor(Color.BLACK)
            }
        }
        return rootView
    }
}

data class FortnightDay(val dayOfMonth: Int, val month: Int, val year: Int, val day: String)

fun Calendar.dayOfMonth(): Int = this.get(Calendar.DAY_OF_MONTH)

fun Calendar.month(): Int = this.get(Calendar.MONTH)

fun Calendar.year(): Int = this.get(Calendar.YEAR)

fun Calendar.addDay(numDays: Int) {
    this.add(Calendar.DATE, numDays)
}

fun Calendar.shortDayName(): String = when (this.get(Calendar.DAY_OF_WEEK) - 1) {
    0 -> "Sun"
    1 -> "Mon"
    2 -> "Tue"
    3 -> "Wed"
    4 -> "Thu"
    5 -> "Fri"
    else -> "Sat"
}

private fun Calendar.toFortnightDay() = FortnightDay(
    dayOfMonth = this.dayOfMonth(),
    month = this.month(),
    year = this.year(),
    day = this.shortDayName())
