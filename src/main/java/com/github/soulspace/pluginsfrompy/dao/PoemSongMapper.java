package com.github.soulspace.pluginsfrompy.dao;

import com.github.soulspace.pluginsfrompy.pojo.PoemSong;

import java.util.List;
import java.util.Map;

public interface PoemSongMapper {
    List<PoemSong> getPoemSongList();

    PoemSong getPoemSongById(int id);

    List<PoemSong> getPoemSongByTitle(Map<String, Object> params);

    List<PoemSong> getPoemSongByAuthor(Map<String, Object> params);

    List<PoemSong> getPoemSongParagraphsLike(Map<String, Object> params);

    int getPoemSongNumberByAuthor(String author);

    int getPoemSongNumberByTitle(String title);
}
