package com.haponenka.app.entity;

import java.util.List;

public interface TextComponent {
  ComponentType getType();

  List<TextComponent> getChildren();

  boolean add(TextComponent component);

  boolean remove(TextComponent component);
}
