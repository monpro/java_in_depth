package com.monpro.effectivejava.objects.abstractbuilder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractBuilderBook {
  final Set<Language> languages;

  AbstractBuilderBook(Builder<?> builder) {
    languages = builder.languages.clone();
  }

  public enum Language {
    ENGLISH,
    CHINESE,
    JAPANESE
  }

  abstract static class Builder<T extends Builder<T>> {
    EnumSet<Language> languages = EnumSet.noneOf(Language.class);

    public T addLanguage(Language language) {
      languages.add(Objects.requireNonNull(language));
      return self();
    }

    abstract AbstractBuilderBook build();

    protected abstract T self();
  }
}
