package com.github.soulspace.pluginsfrompy

import com.github.soulspace.pluginsfrompy.dao.PoemTangMapper
import com.github.soulspace.pluginsfrompy.pojo.PoemTang
import com.github.soulspace.pluginsfrompy.util.MybatisUtil
import org.apache.ibatis.session.SqlSession
import java.util.*

object GetInfoFromDB {
    fun getPoemNumberByAuthor(author: String): Int {
        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val mapper: PoemTangMapper = sqlSession.getMapper(PoemTangMapper::class.java)
        val poemTangNumberByAuthor = mapper.getPoemTangNumberByAuthor(author)
        sqlSession.close()
        return poemTangNumberByAuthor
    }

    fun getPoemByTitle(title: String, startIndex: Int, pageSize: Int): List<PoemTang> {
        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val mapper: PoemTangMapper = sqlSession.getMapper(PoemTangMapper::class.java)
        var params = emptyMap<String, Any>().toMutableMap()
        params["title"] = title
        params["startIndex"] = startIndex
        params["pageSize"] = pageSize
        val poemTangByTitle = mapper.getPoemTangByTitle(params)
        sqlSession.close()
        return poemTangByTitle
    }

    fun getPoemByAuthor(author: String, startIndex: Int, pageSize: Int): List<PoemTang> {
        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val mapper: PoemTangMapper = sqlSession.getMapper(PoemTangMapper::class.java)
        var params = emptyMap<String, Any>().toMutableMap()
        params["author"] = author
        params["startIndex"] = startIndex
        params["pageSize"] = pageSize
        val poemTangByAuthor = mapper.getPoemTangByAuthor(params)
        sqlSession.close()
        return poemTangByAuthor
    }

    fun getPoemNumberByTitle(title: String): Int {
        val sqlSession: SqlSession = MybatisUtil.getSqlSession()
        val mapper: PoemTangMapper = sqlSession.getMapper(PoemTangMapper::class.java)
        val poemTangNumberByTitle = mapper.getPoemTangNumberByTitle(title)
        sqlSession.close()
        return poemTangNumberByTitle
    }
}