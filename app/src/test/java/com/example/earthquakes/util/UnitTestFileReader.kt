package com.example.earthquakes.util

import com.example.earthquakes.reader.BaseFileReader

class UnitTestFileReader : BaseFileReader() {

    override fun getFileAsString(filePath: String): String =
        ClassLoader.getSystemResource(filePath).readText()

}

