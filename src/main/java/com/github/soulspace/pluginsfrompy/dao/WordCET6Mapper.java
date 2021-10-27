package com.github.soulspace.pluginsfrompy.dao;

import com.github.soulspace.pluginsfrompy.pojo.WordCET6;

import java.util.List;
import java.util.Map;

public interface WordCET6Mapper {
    WordCET6 getWordById(int id);

    List<WordCET6> getWordByWord(Map<String, Object> map);

    List<WordCET6> getWordParaphraseLike(Map<String, Object> map);

    List<WordCET6> getWordLimitPage(Map<String, Object> map);
}
