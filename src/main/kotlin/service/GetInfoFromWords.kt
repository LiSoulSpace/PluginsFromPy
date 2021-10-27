package com.github.soulspace.pluginsfrompy.service

import com.github.soulspace.pluginsfrompy.dao.WordCET6Mapper
import com.github.soulspace.pluginsfrompy.util.MybatisUtil
import org.apache.ibatis.session.SqlSession

object GetInfoFromWords {
    fun getRandomWords(number: Int, table: String): List<String> {
        val wordMax = 5526
        val numberRange: IntRange = (1..wordMax)
        val numberList = numberRange.toMutableList()
        val finalRandomNumberList = mutableListOf<Int>()
        for (i in 1..number) {
            val randoms = (0 until numberList.size).random()
            finalRandomNumberList.add(numberList.indexOf(randoms))
            numberList.removeAt(randoms)
        }

        val wordList: MutableList<String> = mutableListOf<String>()

        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val mapper: WordCET6Mapper = sqlSession.getMapper(WordCET6Mapper::class.java)
        for (i in finalRandomNumberList) {
            val wordInfo = mapper.getWordById(i)
            wordList.add(wordInfo.word + "\n" + wordInfo.paraphrase + "\n")
        }
        sqlSession.close()
        return wordList
    }
}