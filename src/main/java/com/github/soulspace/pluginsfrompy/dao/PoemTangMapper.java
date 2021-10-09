package com.github.soulspace.pluginsfrompy.dao;

import com.github.soulspace.pluginsfrompy.pojo.PoemTang;

import java.util.List;
import java.util.Map;

public interface PoemTangMapper {
    List<PoemTang> getPoemTangList();

    PoemTang getPoemTangById(int id);

    List<PoemTang> getPoemTangByTitle(Map<String, Object> map);

    List<PoemTang> getPoemTangByAuthor(Map<String, Object> map);

    List<PoemTang> getPoemTangParagraphsLike(Map<String, Object> map);

    int getPoemTangNumberByAuthor(String author);

    int getPoemTangNumberByTitle(String title);

    int getPoemTangNumberByParagraphsLike(String par);
}
