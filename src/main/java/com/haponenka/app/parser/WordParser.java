package com.haponenka.app.parser;

import com.haponenka.app.entity.ComponentType;
import com.haponenka.app.entity.SymbolLeaf;
import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.entity.TextComposite;

public class WordParser extends AbstractTextParser {

  @Override
  public TextComponent parse(String data) {
    TextComposite word = new TextComposite(ComponentType.WORD);
    for (char symbol : data.toCharArray()) {
      ComponentType type;
      if (Character.isLetter(symbol)) {
        type = ComponentType.LETTER;
      } else if (Character.isDigit(symbol)) {
        type = ComponentType.DIGIT;
      } else {
        type = ComponentType.PUNCTUATION;
      }
      word.add(new SymbolLeaf(type, symbol));
    }
    return word;
  }
}
