package com.github.soulspace.pluginsfrompy

import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader
import java.util.HashMap


@OptIn(net.mamoe.mirai.console.util.ConsoleExperimentalApi::class)
suspend fun main() {
//    MiraiConsoleTerminalLoader.startAsDaemon()
//
//    //如果是Kotlin
//    PluginMain.load()
//    PluginMain.enable()
    //如果是Java
//    JavaPluginMain.INSTANCE.load()
//    JavaPluginMain.INSTANCE.enable()

//    val bot = MiraiConsole.addBot(123456, "") {
//        fileBasedDeviceInfo()
//    }.alsoLogin()
//
//    MiraiConsole.job.join()
    var strmap = HashMap<String, String>()
    strmap.put("3313445307", "temp")
    strmap.put("3", "2")
    println(strmap["3"])
}