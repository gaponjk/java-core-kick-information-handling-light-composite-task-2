package com.haponenka.app.service;

import com.haponenka.app.entity.TextComponent;
import com.haponenka.app.exception.TextException;

/** Operation over parsed text. New tasks are added as new implementations. */
@FunctionalInterface
public interface TextTask<R> {
  R execute(TextComponent text) throws TextException;
}
