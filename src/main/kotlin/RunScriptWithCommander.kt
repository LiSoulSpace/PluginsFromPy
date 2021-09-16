package com.github.soulspace.pluginsfrompy

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object RunScriptWithCommander {
    public fun runPythonScript (pathWithCommand: String): String? {
        val proc: Process
        val commandStr = "python3 $pathWithCommand"
        try {
            proc = Runtime.getRuntime().exec(commandStr)
            val `in` = BufferedReader(InputStreamReader(proc.inputStream))
            var line: String? = null
            var lines: String? = null
            while (`in`.readLine().also { line = it } != null) {
                println(line)
                lines = lines + line + '\n'
            }
            `in`.close()
            proc.waitFor()
            return lines.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return null
    }
}