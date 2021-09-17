package com.github.soulspace.pluginsfrompy

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.NewFriendRequestEvent
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.info
import java.io.File

/**
 * 使用 kotlin 版请把
 * `src/main/resources/META-INF.services/net.mamoe.mirai.console.plugin.jvm.JvmPlugin`
 * 文件内容改成 `org.example.mirai.plugin.PluginMain` 也就是当前主类全类名
 *
 * 使用 kotlin 可以把 java 源集删除不会对项目有影响
 *
 * 在 `settings.gradle.kts` 里改构建的插件名称、依赖库和插件版本
 *
 * 在该示例下的 [JvmPluginDescription] 修改插件名称，id和版本，etc
 *
 * 可以使用 `src/test/kotlin/RunMirai.kt` 在 ide 里直接调试，
 * 不用复制到 mirai-console-loader 或其他启动器中调试
 */
//TODO Change the Description and information
object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "com.github.soulspace.pluginsFromPy",
        name = "pluginsFromPy",
        version = "0.1.2"
    ) {
        author("soulspace")
        info(
            """
            plugins form python
        """.trimIndent()
        )
        // author 和 info 可以删除.
    }
) {
    private val pythonFilesDir = File(System.getProperty("user.dir") + "/data/python-files")
    private val runSWC = RunScriptWithCommander
    override fun onEnable() {
        logger.info { "Plugin loaded" }
        //配置文件目录 "${dataFolder.absolutePath}/"
        val eventChannel = GlobalEventChannel.parentScope(this)
        eventChannel.subscribeAlways<GroupMessageEvent>{
            //群消息
            //复读示例
            if (message.contentToString().startsWith("#wiki ")) {
                val commandBody = message.contentToString().substringAfter("#wiki ")
                if (commandBody == ""){
                    group.sendMessage("请输入要搜索的关键字")
                    return@subscribeAlways
                }
                val pythonFilePath = "$pythonFilesDir/WikipediaGet.py"
                val commandT = "$pythonFilePath $commandBody"
                runSWC.runPythonScript(commandT)
                val imageFileT = File("$pythonFilesDir/WikipediaResult.png")
                group.sendImage(imageFileT)
                logger.info { "#wiki $commandBody repeat over!" }
            }
            else if (message.contentToString().startsWith("#微博")){
                val pythonFilePathWeibo = "$pythonFilesDir/WeiboHotSearch.py"
                runSWC.runPythonScript(pythonFilePathWeibo)
                val imageFileWeibo = File("$pythonFilesDir/WeiboHotSearch.png")
                group.sendImage(imageFileWeibo)
                logger.info{"#微博 repeat over!"}
            }
            else if(message.contentToString().startsWith("#知乎")){
                val pythonFilePathWeibo = "$pythonFilesDir/ZhiHuHotSearch.py"
                runSWC.runPythonScript(pythonFilePathWeibo)
                val imageFileWeibo = File("$pythonFilesDir/ZhiHuHotSearch.png")
                group.sendImage(imageFileWeibo)
                logger.info{"#知乎 repeat over!"}
            }
        }
    }
}
