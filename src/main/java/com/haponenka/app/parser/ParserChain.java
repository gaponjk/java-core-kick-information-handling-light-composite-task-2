package com.haponenka.app.parser;

public final class ParserChain {
  private static final AbstractTextParser CHAIN = build();

  private ParserChain() {}

  public static AbstractTextParser getChain() {
    return CHAIN;
  }

  private static AbstractTextParser build() {
    AbstractTextParser head = new TextParser();
    head.setNext(new ParagraphParser())
        .setNext(new SentenceParser())
        .setNext(new LexemeParser())
        .setNext(new WordParser());
    return head;
  }
}
