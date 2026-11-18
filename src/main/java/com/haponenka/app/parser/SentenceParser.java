package com.haponenka.app.parser;

import com.haponenka.app.entity.ComponentType;
import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.entity.TextComposite;

public class SentenceParser extends AbstractTextParser {
  private static final String LEXEME_DELIMITER_REGEX = "\\s+";

  @Override
  public TextComponent parse(String data) {
    TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
    for (String lexeme : data.strip().split(LEXEME_DELIMITER_REGEX)) {
      if (!lexeme.isEmpty()) {
        sentence.add(getNext().parse(lexeme));
      }
    }
    return sentence;
  }
}
