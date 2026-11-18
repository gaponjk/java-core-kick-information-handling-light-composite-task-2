package com.haponenka.app.parser;

import com.haponenka.app.entity.ComponentType;
import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.entity.TextComposite;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractTextParser {
  private static final String SENTENCE_REGEX = "[^.!?…]+(?:[.!?…]+|$)";
  private static final Pattern SENTENCE_PATTERN = Pattern.compile(SENTENCE_REGEX);

  @Override
  public TextComponent parse(String data) {
    TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
    Matcher matcher = SENTENCE_PATTERN.matcher(data);
    while (matcher.find()) {
      String sentence = matcher.group().strip();
      if (!sentence.isEmpty()) {
        paragraph.add(getNext().parse(sentence));
      }
    }
    return paragraph;
  }
}
