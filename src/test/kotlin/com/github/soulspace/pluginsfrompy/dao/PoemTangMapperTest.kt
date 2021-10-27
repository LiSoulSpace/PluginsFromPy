package com.github.soulspace.pluginsfrompy.dao

import com.github.soulspace.pluginsfrompy.pojo.PoemTang
import com.github.soulspace.pluginsfrompy.util.MybatisUtil
import org.apache.ibatis.session.SqlSession
import org.junit.jupiter.api.Test
import com.github.houbb.opencc4j.util.ZhConverterUtil
import com.github.soulspace.pluginsfrompy.service.GetInfoFromDB
import com.github.soulspace.pluginsfrompy.pojo.Ci
import com.github.soulspace.pluginsfrompy.pojo.WordCET6
import com.github.soulspace.pluginsfrompy.service.GetInfoFromWords
import org.apache.logging.log4j.LogManager


class PoemTangMapperTest {
    private val logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME)

    @Test
    fun test() {
        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val poemTangMapper: PoemTangMapper = sqlSession.getMapper(PoemTangMapper::class.java)
        val poemTangList: List<PoemTang> = poemTangMapper.getPoemTangList()
        for (poemTang in poemTangList) {
            System.out.println(poemTang)
        }
        sqlSession.close()
    }

    @Test
    fun testCET6() {
        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val wordCET6Mapper = sqlSession.getMapper(WordCET6Mapper::class.java)
        var mapT = mutableMapOf<String, Any>()
        mapT["startIndex"] = 0
        mapT["pageSize"] = 20
        val wordByWord: List<WordCET6> = wordCET6Mapper.getWordLimitPage(mapT)
        for (word in wordByWord) {
            System.out.println(word)
        }
        sqlSession.close()
    }

    @Test
    fun getUserById() {
        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val mapper: PoemTangMapper = sqlSession.getMapper(PoemTangMapper::class.java)
        val poemTang: PoemTang = mapper.getPoemTangById(1112)
        System.out.println(poemTang)
        sqlSession.close()
    }

    @Test
    fun getCiByR() {
        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val mapper: CiMapper = sqlSession.getMapper(CiMapper::class.java)
        var mapT = mutableMapOf<String, Any>()
        mapT["rhythmic"] = "佳人醉"
        mapT["startIndex"] = 0
        mapT["pageSize"] = 5
        val ciByRhythmic: List<Ci> = mapper.getCiByRhythmic(mapT)
        System.out.println(ciByRhythmic)
        sqlSession.close()
    }

    @Test
    fun getPoemNumberByAuthor() {
        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val mapper: PoemTangMapper = sqlSession.getMapper(PoemTangMapper::class.java)
        val poemTangParagraphsLike = mapper.getPoemTangNumberByAuthor("李白")
        System.out.println(poemTangParagraphsLike)
        sqlSession.close()
    }

    @Test
    fun getCiByRL() {
        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val mapper: CiMapper = sqlSession.getMapper(CiMapper::class.java)
        var mapT = mutableMapOf<String, Any>()
        mapT["rhythmic"] = "佳人醉"
        mapT["startIndex"] = 0
        mapT["pageSize"] = 5
        val ciByRhythmic = mapper.getCiByRhythmic(mapT)
        ciByRhythmic.forEach {
            println(it.rhythmic + '\n' + it.content)
        }
        sqlSession.close()
    }

    @Test
    fun testZh() {
        val original = "日出行"
        val result: String = ZhConverterUtil.toSimple(original)
        val result2: String = ZhConverterUtil.toTraditional(result)
        System.out.println("$result $result2")
    }

    @Test
    fun getByTitle() {
        val getI = GetInfoFromDB
        val poemByTitle = getI.getPoemByTitle("日出行", 0, 5)
        System.out.println(poemByTitle)
    }

    @Test
    fun log4j2Test() {
        logger.trace("trace level")
        logger.debug("debug level")
        logger.info("info level")
        logger.warn("warn level")
        logger.error("error level")
        logger.fatal("fatal level")
    }

    @Test
    fun randomNumber() {
        var numberRange: IntRange = (1..5526)
        val numberList = numberRange.toMutableList()
        print("Randoms: ")
        for (i in 1..10) {
            print(numberList.size.toString())
            val randoms = (1..numberList.size).random()
            print(" ")
            print(numberList.indexOf(randoms))
            numberList.removeAt(randoms)
            print("\n")
        }
        println()
    }

    @Test
    fun getRandomWords() {
        val t = GetInfoFromWords.getRandomWords(10, "cet6")
        println("t.size:" + t.size.toString() + "\n")
        var answerString = ""
        for (i in (t.indices)) {
            answerString += t.elementAt(i) + "\n"
        }
        println(answerString)
    }
}
