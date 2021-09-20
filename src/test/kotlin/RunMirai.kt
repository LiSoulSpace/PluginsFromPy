package com.github.soulspace.pluginsfrompy

import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader
import java.util.HashMap
import java.util.regex.Pattern
import java.util.regex.*


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
    val nowMsg = "[mirai:source:[6377],[41312341]]但是我不知 道怎么改能让机器人发出来"
    val patter = Pattern.compile("\\[mirai:source(.*?)\\]")
    val matcherT = patter.matcher(nowMsg)
    matcherT.find()
    println(matcherT.start())
    println(matcherT.end())
    println(nowMsg.substring(matcherT.start(), matcherT.end()))
}