package com.example.earthquakes.util

import androidx.test.platform.app.InstrumentationRegistry
import com.example.earthquakes.parser.BaseFileReader

class InstrumentedFileReader: BaseFileReader() {

    override fun getFileAsString(filePath: String): String =
        InstrumentationRegistry.getInstrumentation().context.classLoader
            .getResource(filePath).readText()

}

