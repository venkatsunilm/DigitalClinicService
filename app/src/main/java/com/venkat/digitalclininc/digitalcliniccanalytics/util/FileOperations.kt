package com.venkat.digitalclininc.digitalcliniccanalytics.util

import android.os.Environment
import android.util.Log
import com.venkat.digitalclininc.digitalcliniccanalytics.dto.InfoDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class FileOperations(
    private val screenInfoMap: Map<String, InfoDto.ScreenInfo>,
    private val fileName: String = "AnalyticsProject.json"
) {

    // TODO: Use Fileprovider to secure sharing of files associated with an app by creating a content:// Uri
    private var fileObject: File
    private var pathName = fileName
    val gson = Gson()

    init {
        pathName = Environment.getExternalStorageDirectory().toString()
        fileObject = File(pathName, fileName)
        createFile()
        writeFile()
        readFile()
        closeFile()
    }

    private fun isFileExists(): Boolean {
        return when {
            File(pathName, fileName).exists() -> true
            else -> false
        }
    }

    private fun createFile() {
        when {
            !isFileExists() -> {
                fileObject.createNewFile()
            }
        }
    }

    private fun writeFile() {
        when {
            isFileExists() -> {
                val jsonFromMap = gson.toJson(screenInfoMap)
                fileObject.writeText(jsonFromMap)
            }
        }
    }

    private fun readFile() {
        when {
            isFileExists() -> {
                fileObject.forEachLine { println(it) }
                val jsonFromMap = fileObject.readText()

                // TODO: remove: Only for testing... convert this to Hash Map
                val screenInfoMap: Map<String, InfoDto.ScreenInfo> = gson.fromJson(
                    jsonFromMap,
                    object : TypeToken<Map<String, InfoDto.ScreenInfo>>() {}.type
                )
                for ((key, value) in screenInfoMap) {
                    Log.d("Retrieved data", "$key = $value")
                }
            }
        }
    }

    private fun closeFile() {
        when (isFileExists()) {
            else -> {}
        }
    }


}
