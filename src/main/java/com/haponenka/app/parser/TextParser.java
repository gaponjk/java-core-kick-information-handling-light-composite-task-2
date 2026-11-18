package com.haponenka.app.parser;

import com.haponenka.app.entity.ComponentType;
import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.entity.TextComposite;

public class TextParser extends AbstractTextParser {
  private static final String PARAGRAPH_DELIMITER_REGEX = "\\R(?=[\\t ]+\\S)|\\R\\s*\\R";

  @Override
  public TextComponent parse(String data) {
    TextComposite text = new TextComposite(ComponentType.TEXT);
    for (String paragraph : data.split(PARAGRAPH_DELIMITER_REGEX)) {
      String trimmed = paragraph.strip();
      if (!trimmed.isEmpty()) {
        text.add(getNext().parse(trimmed));
      }
    }
    return text;
  }
}
