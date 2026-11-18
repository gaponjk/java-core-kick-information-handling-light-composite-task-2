package com.haponenka.app.entity;

public enum ComponentType {
  TEXT("", "\n"),
  PARAGRAPH("\t", " "),
  SENTENCE("", " "),
  LEXEME("", ""),
  WORD("", ""),
  LETTER("", ""),
  DIGIT("", ""),
  PUNCTUATION("", "");

  private final String prefix;
  private final String delimiter;

  ComponentType(String prefix, String delimiter) {
    this.prefix = prefix;
    this.delimiter = delimiter;
  }

  public String getPrefix() {
    return prefix;
  }

  public String getDelimiter() {
    return delimiter;
  }
}
