package com.github.soulspace.pluginsfrompy

import com.github.houbb.opencc4j.util.ZhConverterUtil
import com.github.soulspace.pluginsfrompy.pojo.PoemTang
import com.github.soulspace.pluginsfrompy.service.GetInfoFromDB
import com.github.soulspace.pluginsfrompy.service.GetInfoFromWords
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.NudgeEvent
import net.mamoe.mirai.message.code.MiraiCode.deserializeMiraiCode
import net.mamoe.mirai.utils.info
import java.io.File
import java.util.HashMap

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
        version = "1.1.0"
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
    private var groupRepeatMap = HashMap<Long, GroupRepeatInformation>()
    private val getInfoFromDB = GetInfoFromDB
    override fun onEnable() {
        logger.info { "Plugin loaded" }
        //配置文件目录 "${dataFolder.absolutePath}/"
        val eventChannel = GlobalEventChannel.parentScope(this)
        eventChannel.subscribeAlways<GroupMessageEvent> {
            //群消息
            //复读示例
            if (message.contentToString().startsWith("#")) {
                if (message.contentToString().startsWith("#wiki ")) {
                    val commandBody = message.contentToString().substringAfter("#wiki ")
                    if (commandBody == "") {
                        group.sendMessage("请输入要搜索的关键字")
                        return@subscribeAlways
                    }
                    val pythonFilePath = "$pythonFilesDir/WikipediaGet.py"
                    val commandT = "$pythonFilePath $commandBody"
                    runSWC.runPythonScript(commandT)
                    val imageFileT = File("$pythonFilesDir/WikipediaResult.png")
                    group.sendImage(imageFileT)
                    logger.info { "#wiki $commandBody repeat over!" }
                } else if (message.contentToString().equals("#weibo", true)) {
                    val pythonFilePathWeibo = "$pythonFilesDir/WeiboHotSearch.py"
                    runSWC.runPythonScript(pythonFilePathWeibo)
                    val imageFileWeibo = File("$pythonFilesDir/WeiboHotSearch.png")
                    group.sendImage(imageFileWeibo)
                    logger.info { "#weibo repeat over!" }
                } else if (message.contentToString().equals("#zhihu", true)) {
                    val pythonFilePathWeibo = "$pythonFilesDir/ZhiHuHotSearch.py"
                    runSWC.runPythonScript(pythonFilePathWeibo)
                    val imageFileWeibo = File("$pythonFilesDir/ZhiHuHotSearch.png")
                    group.sendImage(imageFileWeibo)
                    logger.info { "#zhihu repeat over!" }
                } else if (message.contentToString().equals("#baidu", true)) {
                    val pythonFilePathWeibo = "$pythonFilesDir/BaiduHotSearch.py"
                    runSWC.runPythonScript(pythonFilePathWeibo)
                    val imageFileWeibo = File("$pythonFilesDir/BaiduHotSearch.png")
                    group.sendImage(imageFileWeibo)
                    logger.info { "#baidu repeat over!" }
                } else if (message.contentToString().equals("#leetcode", true)) {
                    val pythonFilePathDailyQ = "$pythonFilesDir/DailyQuestionGet.py"
                    runSWC.runPythonScript(pythonFilePathDailyQ)
                    val imageFileDailyQ = File("$pythonFilesDir/DailyQuestionGet.png")
                    group.sendImage(imageFileDailyQ)
                    logger.info{"hello git!"}
                    logger.info{"hello git2!"}
                    logger.info { "#leetcode repeat over!" }
                }
            } else {
                if (groupRepeatMap.keys.contains(group.id)) {
                    groupRepeatMap[group.id]!!.lastMsg = groupRepeatMap[group.id]!!.thisMsg
                    groupRepeatMap[group.id]!!.thisMsg = message.serializeToMiraiCode()
                    logger.info { message.serializeToMiraiCode() }
                    if (groupRepeatMap[group.id]!!.lastMsg != groupRepeatMap[group.id]!!.thisMsg)
                        groupRepeatMap[group.id]!!.stopMsg = ""
                    else {
                        if (groupRepeatMap[group.id]!!.thisMsg != groupRepeatMap[group.id]!!.stopMsg) {
                            groupRepeatMap[group.id]!!.stopMsg = groupRepeatMap[group.id]!!.thisMsg
                            group.sendMessage(deserializeMiraiCode(message.serializeToMiraiCode()))
                        }
                    }
                } else {
                    val temp = GroupRepeatInformation("", "", "")
                    groupRepeatMap[group.id] = temp
                }
            }
        }

        eventChannel.subscribeAlways<GroupMessageEvent> {
            if (message.contentToString().startsWith("#")) {
                if (message.contentToString().startsWith("#poem ")) {
                    var result: String = ""
                    val commandBody = message.contentToString().substringAfter("#poem ")
                    val commands = commandBody.split(" ")
                    val commandsSize = commands.size
                    if (commands[0] == "findByTitle") {
                        var poemByTitle: List<PoemTang> = emptyList()
                        if (commands[1] == "poem_tang" || commands[1] == "poem_song") {
                            var title: String = commands[2]
                            title = ZhConverterUtil.toTraditional(title)
                            val poemNumberByTitle = getInfoFromDB.getPoemNumberByTitle(title)
                            var pageNumAll = poemNumberByTitle / 5
                            if (poemNumberByTitle % 5 != 0) pageNumAll += 1
                            if (commandsSize == 3) {
                                poemByTitle = getInfoFromDB.getPoemByTitle(title, 0, 5)
                                result += "页数: 1 / $pageNumAll\n"
                            } else if (commandsSize == 4) {
                                try {
                                    val pageNumber = commands[3].toInt()
                                    if (pageNumber <= 0) {
                                        group.sendMessage("请输入正确的页数")
                                        return@subscribeAlways
                                    }
                                    poemByTitle = getInfoFromDB.getPoemByTitle(title, pageNumber * 5 - 5, 5)
                                    result += "页数: $pageNumber / $pageNumAll\n"
                                } catch (e: NumberFormatException) {
                                    group.sendMessage("请输入正确的页数")
                                    return@subscribeAlways
                                }
                            }
                            poemByTitle.forEach {
                                result += it.title + '\n'
                                result += it.author + '\n'
                                result += it.paragraphs
                                result += '\n'
                            }
                            val f = File("$pythonFilesDir/poems.txt")
                            result = ZhConverterUtil.toSimple(result)
                            f.writeText(result)
                            val pythonFileToImage = "$pythonFilesDir/ToImage.py"
                            runSWC.runPythonScript(pythonFileToImage)
                            val imagePoems = File("$pythonFilesDir/poems.png")
                            group.sendImage(imagePoems)
                        }
                    } else if (commands[0] == "findByAuthor") {
                        var poemByAuthor: List<PoemTang> = emptyList()
                        if (commands[1] == "poem_tang" || commands[1] == "poem_song") {
                            var author: String = commands[2]
                            author = ZhConverterUtil.toTraditional(author)
                            val poemNumberByAuthor = getInfoFromDB.getPoemNumberByAuthor(author)
                            var pageNumAll = poemNumberByAuthor / 5
                            if (poemNumberByAuthor % 5 != 0) pageNumAll += 1
                            if (commandsSize == 3) {
                                poemByAuthor = getInfoFromDB.getPoemByAuthor(author, 0, 5)
                                result += "页数: 1 / $pageNumAll\n"
                            } else if (commandsSize >= 4) {
                                try {
                                    val pageNumber = commands[3].toInt()
                                    if (pageNumber <= 0) {
                                        group.sendMessage("请输入正确的页数")
                                        return@subscribeAlways
                                    }
                                    poemByAuthor =
                                        getInfoFromDB.getPoemByAuthor(author, pageNumber * 5 - 5, 5)
                                    result += "页数: $pageNumber / $pageNumAll\n"
                                } catch (e: NumberFormatException) {
                                    group.sendMessage("请输入正确的页数")
                                    return@subscribeAlways
                                }
                            }
                            poemByAuthor.forEach {
                                result += it.title + '\n'
                                result += it.author + '\n'
                                result += it.paragraphs
                                result += '\n'
                            }
                            val f = File("$pythonFilesDir/poems.txt")
                            result = ZhConverterUtil.toSimple(result)
                            f.writeText(result)
                            val pythonFileToImage = "$pythonFilesDir/ToImage.py"
                            runSWC.runPythonScript(pythonFileToImage)
                            val imagePoems = File("$pythonFilesDir/poems.png")
                            group.sendImage(imagePoems)
                        } else if (commands[1] == "ci") {
                            group.sendMessage("还没写呢")
                        } else if (commands[1] == "all") {
                            group.sendMessage("暂时没有其他可以搜索的库")
                        }
                    } else if (commands[0] == "author") {
                        group.sendMessage("还没写呢")
                    } else if (commands[0] == "next") {
                        group.sendMessage("还没写呢")
                    } else {
                        group.sendMessage("请输入正确的命令！")
                    }
                }
            }
        }

        eventChannel.subscribeAlways<GroupMessageEvent> {
            if (message.contentToString().startsWith("#")) {
                if (message.contentToString().startsWith("#words ")) {
                    val commandBody = message.contentToString().substringAfter("#words ")
                    val commands = commandBody.split(" ")
                    val commandsSize = commands.size
                    if (commands[0] == "cet4" || commands[0] == "cet6") {
                        if (commands[1] == "random") {
                            var wordNumber = 10
                            if (commandsSize == 3 || commandsSize == 2) {
                                if (commandsSize == 3) {
                                    wordNumber = commands[2].toInt()
                                    if (wordNumber <= 0) {
                                        group.sendMessage("请输入正确的个数")
                                        return@subscribeAlways
                                    }
                                }
                                val wordsList = GetInfoFromWords.getRandomWords(wordNumber, "cet6")
                                var answerString: String = ""
                                for (i in (wordsList.indices)) {
                                    answerString += wordsList.elementAt(i) + "\n"
                                }
                                val txtName: String = "randomWords.txt"
                                val aimPicName: String = "randomWords.png"
                                val f = File("$pythonFilesDir/$txtName")
                                f.writeText(answerString)
                                val pythonFileToImage = "$pythonFilesDir/toImageByTxt.py"
                                val pythonCommand = "$pythonFileToImage $txtName $aimPicName"
                                runSWC.runPythonScript(pythonCommand)
                                val imageRandomWords = File("$pythonFilesDir/$aimPicName")
                                group.sendImage(imageRandomWords)
                                logger.info { "words random $wordNumber finished." }
                            }
                        } else if (commands[1] == "search") {
                            group.sendMessage("mei xie ne")
                        } else {
                            group.sendMessage("mei xie ne")
                        }
                    } else {
                        group.sendMessage("请输入正确命令")
                    }
                }
            }
        }

        eventChannel.subscribeAlways<FriendMessageEvent> {

        }

        eventChannel.subscribeAlways<NudgeEvent> {
            subject.sendMessage("您好，这里是机器人，基于框架mirai，现在仍然处于测试阶段。\nOperating by lisoulspace")
        }
    }
}
