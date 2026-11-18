package com.haponenka.app;

import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.exception.TextException;
import com.haponenka.app.parser.ParserChain;
import com.haponenka.app.reader.TextFileReader;
import com.haponenka.app.service.TextStatisticsService;
import com.haponenka.app.service.impl.MaxSentencesWithSameWordTask;
import com.haponenka.app.service.impl.SortSentencesByLetterTask;
import com.haponenka.app.service.impl.SwapFirstLastLexemeTask;
import com.haponenka.app.service.impl.TextStatisticsServiceImpl;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
  private static final Logger logger = LogManager.getLogger();
  private static final String FILE_PATH = "src/main/resources/data/text.txt";

  public static void main(String[] args) {
    try {
      String data = new TextFileReader().read(FILE_PATH);
      TextComponent text = ParserChain.getChain().parse(data);
      logger.info("Restored text:\n{}", text);

      TextStatisticsService statistics = new TextStatisticsServiceImpl();
      logger.info("Letters: {}", statistics.countLetters(text));
      logger.info("Symbols: {}", statistics.countSymbols(text));

      int max = new MaxSentencesWithSameWordTask().execute(text);
      logger.info("Task 1. Max sentences with same words: {}", max);

      List<TextComponent> sorted = new SortSentencesByLetterTask('a').execute(text);
      logger.info("Task 2. Sentences sorted by letter 'a':");
      for (TextComponent sentence : sorted) {
        logger.info("  {}", sentence);
      }

      TextComponent swapped = new SwapFirstLastLexemeTask().execute(text);
      logger.info("Task 3. Swapped first and last lexemes:\n{}", swapped);
    } catch (TextException e) {
      logger.error("Application error", e);
    }
  }
}
