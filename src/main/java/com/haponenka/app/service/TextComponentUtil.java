package com.haponenka.app.service;

import com.haponenka.app.entity.ComponentType;
import com.haponenka.app.entity.TextComponent;
import java.util.ArrayList;
import java.util.List;

public final class TextComponentUtil {
  private TextComponentUtil() {}

  public static List<TextComponent> collect(TextComponent root, ComponentType type) {
    List<TextComponent> result = new ArrayList<>();
    collect(root, type, result);
    return result;
  }

  private static void collect(
      TextComponent component, ComponentType type, List<TextComponent> result) {
    if (component.getType() == type) {
      result.add(component);
      return;
    }
    for (TextComponent child : component.getChildren()) {
      collect(child, type, result);
    }
  }
}
