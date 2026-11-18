package com.haponenka.app.service.impl;

import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.entity.TextComposite;
import com.haponenka.app.service.TextTask;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Task 3: in every sentence swaps the first lexeme with the last one. Returns a new text, the
 * source text is not changed. Sentence length stays the same.
 */
public class SwapFirstLastLexemeTask implements TextTask<TextComponent> {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public TextComponent execute(TextComponent text) {
    TextComposite result = new TextComposite(text.getType());
    for (TextComponent paragraph : text.getChildren()) {
      TextComposite newParagraph = new TextComposite(paragraph.getType());
      for (TextComponent sentence : paragraph.getChildren()) {
        newParagraph.add(swap(sentence));
      }
      result.add(newParagraph);
    }
    logger.info("First and last lexemes swapped");
    return result;
  }

  private TextComponent swap(TextComponent sentence) {
    List<TextComponent> lexemes = sentence.getChildren();
    TextComposite newSentence = new TextComposite(sentence.getType());
    int last = lexemes.size() - 1;
    for (int i = 0; i <= last; i++) {
      if (i == 0) {
        newSentence.add(lexemes.get(last));
      } else if (i == last) {
        newSentence.add(lexemes.getFirst());
      } else {
        newSentence.add(lexemes.get(i));
      }
    }
    return newSentence;
  }
}
