package com.example.testtaskandroid.fetchData

import android.os.Environment
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

object ConfigureSettings {
    var configureSettingsInfo: ConfigureSettingsModel? = null
    var statusConnectionInfo: StatusConnectionModel? = null

    private val _filePath = "${Environment.getDataDirectory()}/data" +
                            "/com.example.testtaskandroid/files"

    init {
        getConfigureSettingsFromFile()
        getStatusConnectionFromFile()
    }

    fun updateConfigureSettingsFile(firstParam: String, secondParam: String, thirdParam: String) {
        try {
            if (configureSettingsInfo == null) {
                configureSettingsInfo =
                    ConfigureSettingsModel(firstParam, secondParam, thirdParam)
            } else {
                configureSettingsInfo?.firstParam = firstParam
                configureSettingsInfo?.secondParam = secondParam
                configureSettingsInfo?.thirdParam = thirdParam
            }

            val mapper = jacksonObjectMapper()
            val serialized = mapper.writeValueAsString(configureSettingsInfo)

            val fileObject = File("${_filePath}/configure_settings.json")
            if (!fileObject.exists()) {
                fileObject.createNewFile()
            }
            fileObject.writeText(serialized)
        } catch (e: Exception) {
            println(e)
        }
    }

    fun updateStatusConnectionFile(isAvailable: Boolean, dateTime: String) {
        try {
            if (statusConnectionInfo == null) {
                statusConnectionInfo =
                    StatusConnectionModel(isAvailable, dateTime)
            } else {
                statusConnectionInfo?.isAvailable = isAvailable
                statusConnectionInfo?.timeUpdate = dateTime
            }

            val mapper = jacksonObjectMapper()
            val serialized = mapper.writeValueAsString(statusConnectionInfo)

            val fileObject = File("${_filePath}/status_connection.json")
            if (!fileObject.exists()) {
                fileObject.createNewFile()
            }
            fileObject.writeText(serialized)
        } catch (e: Exception) {
            println(e)
        }
    }

    private fun getConfigureSettingsFromFile() {
        try {
            val fileObject = File("${_filePath}/configure_settings.json")
            if (!fileObject.exists()) {
                fileObject.createNewFile()
            }
            val jsonSettingsInfo = fileObject.readText()

            if (jsonSettingsInfo.isEmpty())
                return

            val mapper = jacksonObjectMapper()
            configureSettingsInfo = mapper.readValue<ConfigureSettingsModel>(jsonSettingsInfo)

        } catch (e: Exception) {
            println(e)
        }
    }

    private fun getStatusConnectionFromFile() {
        try {
            val fileObject = File("${_filePath}/status_connection.json")
            if (!fileObject.exists()) {
                fileObject.createNewFile()
            }
            val jsonSettingsInfo = fileObject.readText()

            if (jsonSettingsInfo.isEmpty())
                return

            val mapper = jacksonObjectMapper()
            statusConnectionInfo = mapper.readValue<StatusConnectionModel>(jsonSettingsInfo)

        } catch (e: Exception) {
            println(e)
        }
    }
}