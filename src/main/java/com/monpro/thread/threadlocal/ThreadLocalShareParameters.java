package com.monpro.thread.threadlocal;

public class ThreadLocalShareParameters {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.register();
        OrderService orderService = new OrderService();
        orderService.order();
        PaymentService paymentService = new PaymentService();
        paymentService.pay();
    }
}

class UserService {

    public void register() {
        User user = new User("monpro");
        UserContextHolder.holder.set(user);
    }
}

class OrderService {

    public void order() {
        User user = UserContextHolder.holder.get();
        System.out.println(user.name + " make order");
    }
}

class PaymentService {

    public void pay() {
        User user = UserContextHolder.holder.get();
        System.out.println(user.name + " make payment");
    }
}

class User {
    String name;

    User(String name) {
        this.name = name;
    }
}

class UserContextHolder {
    // no need to rewrite initialValue
    // use set and get
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}
