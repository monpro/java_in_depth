package com.monpro.effectivejava.objects;

import java.util.Map;
import java.util.Objects;

public class DependencyInjection {
  /**
   * Many classes have dependency on underlying resources Then use static or singleton is inflexible
   * because the underlying resource might change So one of a good practice is pass the resource
   * into the constructor into the constructor when creating a new instance This is called
   * dependency injection. The resource is injected into the class when it's created
   */

  /** This one form of dependency injection */
  private final Map map;

  public DependencyInjection(Map map) {
    this.map = Objects.requireNonNull(map);
  }

  /** Spring handle the dependency injection by using the autowired */
  //
  //  @Autowired private Map map;
  //
  //  public DependencyInjection() {}
}
