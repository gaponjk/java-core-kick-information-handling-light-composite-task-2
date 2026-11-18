package com.haponenka.app.service;

import com.haponenka.app.entity.TextComponent;

public interface TextStatisticsService {
  long countLetters(TextComponent text);

  long countSymbols(TextComponent text);
}
