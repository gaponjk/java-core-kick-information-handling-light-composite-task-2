package com.haponenka.app.service.impl;

import com.haponenka.app.entity.ComponentType;
import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.service.TextComponentUtil;
import com.haponenka.app.service.TextStatisticsService;

public class TextStatisticsServiceImpl implements TextStatisticsService {

  @Override
  public long countLetters(TextComponent text) {
    return TextComponentUtil.collect(text, ComponentType.LETTER).size();
  }

  @Override
  public long countSymbols(TextComponent text) {
    return TextComponentUtil.collect(text, ComponentType.LETTER).size()
        + TextComponentUtil.collect(text, ComponentType.DIGIT).size()
        + TextComponentUtil.collect(text, ComponentType.PUNCTUATION).size();
  }
}
