package com.jvm;

/**
 * @author weijiaming
 * @since 2020/8/31 15:30
 *  == 和 equals比较
 */
public class CompareStringSample {

    public static void main(String[] args) {

        /**
         *  针对String类型
         *  ==：比较其内存地址
         *  equals：比较其值。长度一样比较每一个字符
         **/

        /**
         *  1、"abc"存放在运行时常量池中，string1引用常量池中的"abc"的内存地址
         *  2、string2发现常量池中已经有"abc"，直接引用该"abc"
         *  故而string1和string2实际上引用的是同一个对象
         *  true/true
         **/
        String string1 = "abc";
        String string2 = "abc";
        System.out.println(string1 == string2);
        System.out.println(string1.equals(string2));

        /**
         *  string3对象存放在堆里面，其值"abc"引用常量池
         *  故而string3和string1值相等，而内存地址实际上是不一样的
         *  false/true
         **/
        System.out.println();
        String string3 = new String("abc");
        System.out.println(string1 == string3);
        System.out.println(string1.equals(string3));

        /**
         *  String4是new出来的一个新的对象，存放在堆里面
         *  和string3值一样，但内存地址不同
         *  false/true
         **/
        System.out.println();
        String string4 = new String("abc");
        System.out.println(string3 == string4);
        System.out.println(string3.equals(string4));

        /**
         *  string5在编译时，会被优化，变成"abc"
         *  常量池中有"abc"，所以string5直接引用常量池中的"abc"
         *  true/true
         **/
        System.out.println();
        String string5 = "a" + "b" + "c";
        System.out.println(string1 == string5);
        System.out.println(string1.equals(string5));

        /**
         *  string6引用堆中的一个内存地址。
         *  该地址又引用了两个地址，其一："ab"存入常量池中并引用该内存地址；其二是堆中的另一个对象，该对象引用常量池中的"c"
         *  string6和string1值相等，地址不能
         *  string6和string3值相等，地址不等
         *  false/true
         *  false/true
         **/
        System.out.println();
        String string6 = "ab" + new String("c");
        System.out.println(string1 == string6);
        System.out.println(string1.equals(string6));
        System.out.println(string3 == string6);
        System.out.println(string3.equals(string6));

        /**
         * 和string6一样
         **/
        System.out.println();
        String string7 = new String("ab") + "c";
        System.out.println(string1 == string7);
        System.out.println(string1.equals(string7));
        System.out.println(string3 == string7);
        System.out.println(string3.equals(string7));

        /**
         *  intern()方法：如果常量池中有该值，直接返回该值的引用
         *  所以string8和string1都是引用常量池中的值，string3是堆中的引用
         *  true/true
         *  false/true
         **/
        System.out.println();
        String string8 = string3.intern();
        System.out.println(string1 == string8);
        System.out.println(string1.equals(string8));
        System.out.println(string3 == string8);
        System.out.println(string3.equals(string8));

    }


}
