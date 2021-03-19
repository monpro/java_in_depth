package com.monpro.effectivejava.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StaticFactoryMethods {

  /**
   * 5. the class of returned object need not exist when the classing containing the method is
   *
   * <p>written It means that the API of your static factory method can return an interface type, of
   * which the implementation won't be written or generated until later.
   *
   * <p>As an example:
   *
   * In this case, the factory method only needs the MyInterface interface to exist when it's
   * being compiled. The actual implementation can be loaded dynamically at runtime in many ways,
   * including:
   */
  public static MyInterface getMyInterfaceInstance() {
    // load instance dynamically and return it.
    return null;
  }

  /**
   * static factory method to create objects have such advantages:
   *
   * <p>1.It has names unlike constructors 2.It is not required to create a new object each time
   * they're invoked
   */
  public Boolean valueOf(int val) {
    return val == 0 ? Boolean.TRUE : Boolean.FALSE;
  }

  /**
   * 3.It can return an object of any subtype of there return type
   *
   * @param arr
   * @return List
   */
  public List from(int[] arr) {
    return new ArrayList<>(Collections.singletonList(arr));
  }

  /**
   * 4.the return object can vary from call to call as a function of the input parameters
   *
   * @param arr
   * @return List
   */
  public List instanceOf(int[] arr) {
    if (arr.length >= 2) {
      return new ArrayList<>(Collections.singletonList(arr));
    } else {
      return new LinkedList<>(Collections.singletonList(arr));
    }
  }
}
