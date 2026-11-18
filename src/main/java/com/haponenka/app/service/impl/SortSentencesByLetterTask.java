package com.haponenka.app.service.impl;

import com.haponenka.app.entity.ComponentType;
import com.haponenka.app.entity.SymbolLeaf;
import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.exception.TextException;
import com.haponenka.app.service.TextComponentUtil;
import com.haponenka.app.service.TextTask;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Task 2: sentences sorted by ascending count of the given letter. */
public class SortSentencesByLetterTask implements TextTask<List<TextComponent>> {
  private static final Logger logger = LogManager.getLogger();
  private final char letter;

  public SortSentencesByLetterTask(char letter) {
    this.letter = Character.toLowerCase(letter);
  }

  @Override
  public List<TextComponent> execute(TextComponent text) throws TextException {
    if (!Character.isLetter(letter)) {
      throw new TextException("Not a letter: " + letter);
    }
    List<TextComponent> sentences =
        new ArrayList<>(TextComponentUtil.collect(text, ComponentType.SENTENCE));
    sentences.sort(Comparator.comparingLong(this::countLetter));
    logger.info("Sentences sorted by letter '{}'", letter);
    return sentences;
  }

  private long countLetter(TextComponent sentence) {
    long count = 0;
    for (TextComponent component : TextComponentUtil.collect(sentence, ComponentType.LETTER)) {
      if (Character.toLowerCase(((SymbolLeaf) component).getSymbol()) == letter) {
        count++;
      }
    }
    return count;
  }
}
