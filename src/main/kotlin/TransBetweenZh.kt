package com.github.soulspace.pluginsfrompy

import com.github.houbb.opencc4j.util.ZhConverterUtil

class TransBetweenZh {
    fun simplified2Traditional(sentence: String): String? {
        return ZhConverterUtil.toTraditional(sentence)
    }

    fun traditional2Simplified(sentence: String): String? {
        return ZhConverterUtil.toSimple(sentence)
    }
}