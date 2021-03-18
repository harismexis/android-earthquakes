package com.example.earthquakes.framework.util.filter

import android.text.InputFilter
import android.text.Spanned

class InputFilterMinMax(
    private var min: Int,
    private var max: Int
) : InputFilter {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            var newValue = dest.toString().substring(0, dstart) +
                    dest.toString().substring(dend, dest.toString().length)
            newValue = newValue.substring(0, dstart) +
                    source.toString() +
                    newValue.substring(dstart, newValue.length)
            if (newValue.isEmpty()) return ""
            val input = newValue.toInt()
            if (isInRange(min, max, input)) return null
        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
        }
        return ""
    }

    private fun isInRange(min: Int, max: Int, input: Int): Boolean {
        return if (max > min) input in min..max else input in max..min
    }
}