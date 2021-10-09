package com.github.soulspace.pluginsfrompy.dao;

import com.github.soulspace.pluginsfrompy.pojo.Ci;

import java.util.List;
import java.util.Map;

public interface CiMapper {
    List<Ci> getCiByRhythmic(String rhythmic);

    List<Ci> getCiByAuthor(Map<String, Integer> map);
}
