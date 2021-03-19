package com.monpro.effectivejava.objects.abstractbuilder;

public class EBuilderBook extends AbstractBuilderBook {

  private final int version;

  private EBuilderBook(Builder builder) {
    super(builder);
    version = builder.version;
  }

  public static void main(String[] args) {
    EBuilderBook book = new EBuilderBook.Builder(1).build();
    EBuilderBook bookWithLanguage =
        new EBuilderBook.Builder(1).addLanguage(AbstractBuilderBook.Language.ENGLISH).addLanguage(Language.CHINESE).build();
  }

  public static class Builder extends AbstractBuilderBook.Builder<Builder> {
    private final int version;

    public Builder(int val) {
      this.version = val;
    }

    @Override
    EBuilderBook build() {
      return new EBuilderBook(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }
}
