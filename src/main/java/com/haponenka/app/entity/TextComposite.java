package com.haponenka.app.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextComposite implements TextComponent {
  private final ComponentType type;
  private final List<TextComponent> children = new ArrayList<>();

  public TextComposite(ComponentType type) {
    this.type = type;
  }

  @Override
  public ComponentType getType() {
    return type;
  }

  @Override
  public List<TextComponent> getChildren() {
    return Collections.unmodifiableList(children);
  }

  @Override
  public boolean add(TextComponent component) {
    return children.add(component);
  }

  @Override
  public boolean remove(TextComponent component) {
    return children.remove(component);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TextComposite that = (TextComposite) o;
    return type == that.type && children.equals(that.children);
  }

  @Override
  public int hashCode() {
    int result = type != null ? type.hashCode() : 0;
    result = 31 * result + children.hashCode();
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < children.size(); i++) {
      if (i > 0) {
        builder.append(type.getDelimiter());
      }
      builder.append(children.get(i).getType().getPrefix());
      builder.append(children.get(i));
    }
    return builder.toString();
  }
}
