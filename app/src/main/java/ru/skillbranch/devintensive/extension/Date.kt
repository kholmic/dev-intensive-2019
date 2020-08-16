package ru.skillbranch.devintensive.extension

import android.util.TimeUtils
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var delta:Long = date.time - this.time
    val dlSec:Long = abs(delta) / SECOND
    val dlMin:Long = abs(delta) / MINUTE
    val dlHor:Long = abs(delta) / HOUR
    val dlDay:Long = abs(delta) / DAY
    var cherez : String  = if(delta<0) "через " else ""
    var nazad : String  = if(delta>0) " назад" else ""

    return when{
        dlSec in 0..1 -> "только что"
        dlSec in 1..45 -> "${cherez}несколько секунд"
        dlSec in 45..75 -> "${cherez}минуту${nazad}"
        dlSec > 75 && dlMin < 45 -> "${cherez}${TimeUnits.SECOND.plural(dlMin.toInt())}${nazad}"
        dlMin in 45..75 -> "${cherez}час назад"
        dlMin > 75 && dlHor < 2  -> "${cherez}более часа${nazad}"
        dlHor in 2..22 -> "${cherez}${TimeUnits.HOUR.plural(dlHor.toInt())}${nazad}"
        dlHor > 26 && dlDay < 360 -> "${cherez}${TimeUnits.DAY.plural(dlDay.toInt())}${nazad}"
        dlDay > 360 && delta > 0 -> "более года назад"
        dlDay > 360 && delta < 0 -> "более чем через год"
        else -> "${cherez}не известно когда${nazad}"
    }
}


enum class TimeUnits{
    SECOND{
        override fun plural(value: Int): String {
            val v = value.rem(10)
            return when {
                v == 1 -> "$value секунда"
                v in 2..4 -> "$value секунды"
                v in 5..9 -> "$value секунд"
                else -> "$value секунд"
            }
        }
    },
    MINUTE{
        override fun plural(value: Int): String {
            val v = value.rem(10)
            return when {
                v == 1 -> "$value минута"
                v in 2..4 -> "$value минуты"
                v in 5..9 -> "$value минут"
                else -> "$value минут"
            }
        }
    },
    HOUR{
        override fun plural(value: Int): String {
            val v = value.rem(10)
            return when {
                v == 1 -> "$value час"
                v in 2..4 -> "$value часа"
                v in 5..9 -> "$value часов"
                else -> "$value часов"
            }
        }
    },
    DAY{
        override fun plural(value: Int): String {
            val v = value.rem(10)
            return when {
                v == 1 -> "$value день"
                v in 2..4 -> "$value дня"
                v in 5..9 -> "$value дней"
                else -> "$value дней"
            }
        }
    };
    abstract fun plural(value:Int):String
}