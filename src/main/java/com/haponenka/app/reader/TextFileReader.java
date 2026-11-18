package com.haponenka.app.reader;

import com.haponenka.app.exception.TextException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextFileReader {
  private static final Logger logger = LogManager.getLogger();

  public String read(String filePath) throws TextException {
    if (filePath == null || filePath.isBlank()) {
      throw new TextException("File path is null or empty");
    }
    Path path = Path.of(filePath);
    try {
      String text = Files.readString(path, StandardCharsets.UTF_8);
      logger.info("File {} read, {} chars", filePath, text.length());
      return text;
    } catch (IOException e) {
      logger.error("Can't read file {}", filePath, e);
      throw new TextException("Can't read file " + filePath, e);
    }
  }
}
