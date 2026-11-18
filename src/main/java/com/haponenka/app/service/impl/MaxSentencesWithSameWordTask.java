package com.haponenka.app.service.impl;

import com.haponenka.app.entity.ComponentType;
import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.service.TextComponentUtil;
import com.haponenka.app.service.TextTask;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Task 1: the largest number of sentences that contain the same word. */
public class MaxSentencesWithSameWordTask implements TextTask<Integer> {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public Integer execute(TextComponent text) {
    Map<String, Integer> sentencesPerWord = new HashMap<>();
    for (TextComponent sentence : TextComponentUtil.collect(text, ComponentType.SENTENCE)) {
      Set<String> uniqueWords = new HashSet<>();
      for (TextComponent word : TextComponentUtil.collect(sentence, ComponentType.WORD)) {
        uniqueWords.add(word.toString().toLowerCase());
      }
      for (String word : uniqueWords) {
        sentencesPerWord.merge(word, 1, Integer::sum);
      }
    }
    int max = 0;
    String maxWord = null;
    for (Map.Entry<String, Integer> entry : sentencesPerWord.entrySet()) {
      if (entry.getValue() > max) {
        max = entry.getValue();
        maxWord = entry.getKey();
      }
    }
    logger.info("Max sentences with same word: {} (word '{}')", max, maxWord);
    return max;
  }
}
