package com.jvm;

/**
 * @author ultrajiaming
 * @since 2020/8/29 12:13
 */
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello(String param) {
        System.out.println("User类hello方法执行了，，，，，，，，" + param);
    }

}
