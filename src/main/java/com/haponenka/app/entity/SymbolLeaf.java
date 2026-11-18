package com.haponenka.app.entity;

import java.util.List;

public class SymbolLeaf implements TextComponent {
  private final ComponentType type;
  private final char symbol;

  public SymbolLeaf(ComponentType type, char symbol) {
    this.type = type;
    this.symbol = symbol;
  }

  public char getSymbol() {
    return symbol;
  }

  @Override
  public ComponentType getType() {
    return type;
  }

  @Override
  public List<TextComponent> getChildren() {
    return List.of();
  }

  @Override
  public boolean add(TextComponent component) {
    throw new UnsupportedOperationException("Leaf can't contain components");
  }

  @Override
  public boolean remove(TextComponent component) {
    throw new UnsupportedOperationException("Leaf can't contain components");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SymbolLeaf that = (SymbolLeaf) o;
    return symbol == that.symbol && type == that.type;
  }

  @Override
  public int hashCode() {
    int result = type != null ? type.hashCode() : 0;
    result = 31 * result + Character.hashCode(symbol);
    return result;
  }

  @Override
  public String toString() {
    return String.valueOf(symbol);
  }
}
