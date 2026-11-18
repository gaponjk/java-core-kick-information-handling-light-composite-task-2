package com.haponenka.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.exception.TextException;
import com.haponenka.app.parser.ParserChain;
import com.haponenka.app.service.impl.MaxSentencesWithSameWordTask;
import com.haponenka.app.service.impl.SortSentencesByLetterTask;
import com.haponenka.app.service.impl.SwapFirstLastLexemeTask;
import com.haponenka.app.service.impl.TextStatisticsServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Test;

class TextTasksTest {
  private static final String TEXT = "\tA cat sat. The cat ran away. Dog barks!\n\tThe cat, a dog.";

  private final TextComponent text = ParserChain.getChain().parse(TEXT);

  @Test
  void countsLettersAndSymbols() {
    TextComponent small = ParserChain.getChain().parse("\tHi, 42 you.");
    TextStatisticsService service = new TextStatisticsServiceImpl();
    assertEquals(5, service.countLetters(small));
    assertEquals(9, service.countSymbols(small));
  }

  @Test
  void findsMaxSentencesWithSameWord() {
    assertEquals(3, new MaxSentencesWithSameWordTask().execute(text));
  }

  @Test
  void sortsSentencesByLetter() throws TextException {
    List<TextComponent> sorted = new SortSentencesByLetterTask('a').execute(text);
    assertEquals("Dog barks!", sorted.get(0).toString());
    assertEquals("The cat, a dog.", sorted.get(1).toString());
    assertEquals("A cat sat.", sorted.get(2).toString());
    assertEquals("The cat ran away.", sorted.get(3).toString());
  }

  @Test
  void sortThrowsForNotLetter() {
    assertThrows(TextException.class, () -> new SortSentencesByLetterTask('1').execute(text));
  }

  @Test
  void swapsFirstAndLastLexeme() {
    TextComponent swapped = new SwapFirstLastLexemeTask().execute(text);
    assertEquals(
        "\tsat. cat A away. cat ran The barks! Dog\n\tdog. cat, a The", swapped.toString());
    assertEquals(TEXT.length(), swapped.toString().length());
    assertEquals(TEXT, text.toString());
  }
}
