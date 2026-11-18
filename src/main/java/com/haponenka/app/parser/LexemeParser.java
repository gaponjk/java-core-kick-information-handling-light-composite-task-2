package com.haponenka.app.parser;

import com.haponenka.app.entity.ComponentType;
import com.haponenka.app.entity.SymbolLeaf;
import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.entity.TextComposite;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractTextParser {
  private static final String LEXEME_PART_REGEX =
      "([\\p{L}\\p{N}]+(?:[-'’][\\p{L}\\p{N}]+)*)|(\\S)";
  private static final Pattern LEXEME_PART_PATTERN = Pattern.compile(LEXEME_PART_REGEX);
  private static final int WORD_GROUP = 1;

  @Override
  public TextComponent parse(String data) {
    TextComposite lexeme = new TextComposite(ComponentType.LEXEME);
    Matcher matcher = LEXEME_PART_PATTERN.matcher(data);
    while (matcher.find()) {
      String word = matcher.group(WORD_GROUP);
      if (word != null) {
        lexeme.add(getNext().parse(word));
      } else {
        lexeme.add(new SymbolLeaf(ComponentType.PUNCTUATION, matcher.group().charAt(0)));
      }
    }
    return lexeme;
  }
}
