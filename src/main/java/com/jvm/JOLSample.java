package com.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ultrajiaming
 * @since 2020/8/30 21:32
 * 对象头信息
 *  需要引入依赖：
 *      <dependency>
 *          <groupId>org.openjdk.jol</groupId>
 *          <artifactId>jol-core</artifactId>
 *          <version>0.12</version>
 *      </dependency>
 */
public class JOLSample {

    public static void main(String[] args) {
        System.out.println("Object:");
        ClassLayout classLayout = ClassLayout.parseInstance(new Object());
        System.out.println(classLayout.toPrintable());

        System.out.println("int[]:");
        ClassLayout classLayout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(classLayout1.toPrintable());

        System.out.println("A:");
        ClassLayout classLayout2 = ClassLayout.parseInstance(new A());
        System.out.println(classLayout2.toPrintable());
    }

    /**
     *  -XX:+UseCompressedOops 默认开启的压缩所有指针
     *  -XX:+UseCompressedClassPointers 默认开启的压缩对象头里的类型指针Klass Pointer
     *   Oops : Ordinary Object Pointers
     **/
    static class A {
        //8B mark word
        // 4B Klass Pointer 如果关闭压缩-XX:-UseCompressedClassPointers或-XX:-UseCompressedOops，则占用8B
         int id;//4B
         String name;//4B 如果关闭压缩-XX:-UseCompressedOops，则占用8B
         byte b;//1B
         Object o;//4B 如果关闭压缩-XX:-UseCompressedOops，则占用8B
    }

}
