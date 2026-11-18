package com.haponenka.app.parser;

import com.haponenka.app.entity.TextComponent;

public abstract class AbstractTextParser {
  private AbstractTextParser next;

  public AbstractTextParser setNext(AbstractTextParser next) {
    this.next = next;
    return next;
  }

  protected AbstractTextParser getNext() {
    return next;
  }

  public abstract TextComponent parse(String data);
}
