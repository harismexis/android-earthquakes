package com.example.earthquakes.framework.util.filter

import android.text.InputFilter
import android.text.Spanned

class InputFilterMinMax : InputFilter {
    private var min: Int
    private var max: Int

    constructor(min: Int, max: Int) {
        this.min = min
        this.max = max
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            var newVal = dest.toString().substring(0, dstart) +
                    dest.toString().substring(dend, dest.toString().length)

            newVal = newVal.substring(0, dstart) +
                    source.toString() +
                    newVal.substring(dstart, newVal.length)

            val input = newVal.toInt()

            if (isInRange(min, max, input)) return null

        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
        }
        return ""
    }

    private fun isInRange(a: Int, b: Int, c: Int): Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}