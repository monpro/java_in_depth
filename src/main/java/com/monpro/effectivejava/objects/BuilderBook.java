package com.monpro.effectivejava.objects;

/**
 * The BuilderBook class is immutable all default values are in one place The Builder setter method
 * return itself so the invocations could be chained. It's quite flexible when dealing with multiple
 * parameters, say larger than 4
 */
public class BuilderBook {

  private final int pages;
  private final int weight;
  private final int price;
  private final String author;
  private final String producer;

  private BuilderBook(Builder builder) {
    // do parameters check here
    if (builder.pages < 0) {
      throw new IllegalArgumentException("num of pages should be larger than or equal to 0");
    }
    pages = builder.pages;
    weight = builder.weight;
    price = builder.price;
    author = builder.author;
    producer = builder.producer;
  }

  public static void main(String[] args) {
    BuilderBook book = new BuilderBook.Builder(10, 5).build();
    BuilderBook bookWithPrice = new Builder(10, 5).price(20).build();
    BuilderBook bookWithPriceAndAuthor = new Builder(10, 5).author("Mike").price(20).build();
  }

  public static class Builder {
    // required parameters
    private final int pages;
    private final int weight;

    private int price = 0;
    private String author = "";
    private String producer = "";

    public Builder(int pages, int weight) {
      this.pages = pages;
      this.weight = weight;
    }

    public Builder price(int val) {
      price = val;
      return this;
    }

    public Builder author(String val) {
      author = val;
      return this;
    }

    public Builder producer(String val) {
      producer = val;
      return this;
    }

    public BuilderBook build() {
      return new BuilderBook(this);
    }
  }
}
