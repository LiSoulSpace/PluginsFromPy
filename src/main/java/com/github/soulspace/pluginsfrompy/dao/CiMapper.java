package com.github.soulspace.pluginsfrompy.dao;

import com.github.soulspace.pluginsfrompy.pojo.Ci;
import com.github.soulspace.pluginsfrompy.pojo.Ci;

import java.util.List;
import java.util.Map;

public interface CiMapper {
    List<Ci> getCiList();

    Ci getCiById(int id);

    List<Ci> getCiByRhythmic(Map<String, Object> map);

    List<Ci> getCiByAuthor(Map<String, Object> map);

    List<Ci> getCiContentLike(Map<String, Object> map);

    int getCiNumberByAuthor(String author);

    int getCiNumberByTitle(String title);

    int getCiNumberByParagraphsLike(String paragraph);
}
