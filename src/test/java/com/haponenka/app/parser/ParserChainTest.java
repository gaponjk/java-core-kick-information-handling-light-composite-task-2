package com.haponenka.app.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.haponenka.app.entity.ComponentType;
import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.exception.TextException;
import com.haponenka.app.reader.TextFileReader;
import com.haponenka.app.service.TextComponentUtil;
import org.junit.jupiter.api.Test;

class ParserChainTest {
  private static final String TEXT = "\tHello, world! It is me.\n\tBye.";

  @Test
  void restoresOriginalText() {
    TextComponent text = ParserChain.getChain().parse(TEXT);
    assertEquals(TEXT, text.toString());
  }

  @Test
  void restoresTextFromFile() throws TextException {
    String data = new TextFileReader().read("src/test/resources/data/text.txt");
    TextComponent text = ParserChain.getChain().parse(data);
    assertEquals(data.stripTrailing(), text.toString());
  }

  @Test
  void replacesMultipleSpacesAndTabsWithOneSpace() {
    TextComponent text = ParserChain.getChain().parse("\tOne   two\t\tthree.");
    assertEquals("\tOne two three.", text.toString());
  }

  @Test
  void splitsIntoComponents() {
    TextComponent text = ParserChain.getChain().parse(TEXT);
    assertEquals(2, TextComponentUtil.collect(text, ComponentType.PARAGRAPH).size());
    assertEquals(3, TextComponentUtil.collect(text, ComponentType.SENTENCE).size());
    assertEquals(6, TextComponentUtil.collect(text, ComponentType.LEXEME).size());
    assertEquals(6, TextComponentUtil.collect(text, ComponentType.WORD).size());
  }

  @Test
  void hyphenatedWordIsOneWord() {
    TextComponent text = ParserChain.getChain().parse("\tmore-or-less.");
    assertEquals(1, TextComponentUtil.collect(text, ComponentType.WORD).size());
  }

  @Test
  void chainIsCreatedOnce() {
    var first = ParserChain.getChain();
    var second = ParserChain.getChain();
    assertSame(first, second);
  }
}
