package com.revolna

import java.io.FileInputStream
import java.util.*

object PRIVATE_KEYS {
    // Keys  what not showed in git.
    val configFilePath = "local.properties"
    val propsInput = FileInputStream(configFilePath)
    val properties = Properties()


    var HOSTNAME_HIDE      = loadConfig("HOSTNAME_HIDE")
    var DATABASE_NAME_HIDE = loadConfig("DATABASE_NAME_HIDE")
    var USERNAME_HIDE      = loadConfig("USERNAME_HIDE")
    var PASSWORD_HIDE      = loadConfig("PASSWORD_HIDE")

    fun loadConfig(parameter : String) : String {
        properties.load(propsInput)
        println("<<<<<<<> ${properties.getProperty(parameter)}")
        return properties.getProperty(parameter)
    }
}

