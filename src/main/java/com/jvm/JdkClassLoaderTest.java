package com.jvm;

import sun.security.ec.ECKeyFactory;

/**
 * @author weijiaming
 * @since 2020/8/28 15:51
 * 查看jdk各种类加载器
 */
public class JdkClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println("java.lang.String (引导)类加载器：" + stringClassLoader); //null (引导类加载器是c语言生成的，所以此处java获取不到)
        ClassLoader extClassLoader = new ECKeyFactory().getClass().getClassLoader();
        System.out.println("sun.security.ec.ECKeyFactory  (扩展)类加载器：" + extClassLoader.toString()); //sun.misc.Launcher$ExtClassLoader@4b67cf4d
        ClassLoader applicationClassLoader = JdkClassLoaderTest.class.getClassLoader();
        System.out.println("com.jvm.JdkClassLoaderTest (应用程序)类加载器：" + applicationClassLoader.toString()); //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("系统默认类加载器：" + ClassLoader.getSystemClassLoader()); //sun.misc.Launcher$AppClassLoader@18b4aac2

        // 环境变量
        System.out.println();
        System.out.println();
        // System.getenv() 获取环境变量
        System.out.println("JAVA_HOME------" + System.getenv("JAVA_HOME"));
        System.out.println("USERNAME------" + System.getenv("USERNAME"));
        System.out.println("Path------" + System.getenv("Path"));
        System.out.println();
        // System.getProperty() 获取系统变量
        System.out.println("sun.boot.class.path(引导类加载器加载路径)------" + System.getProperty("sun.boot.class.path"));
        System.out.println("java.ext.dirs(扩展类加载器加载路径)------" + System.getProperty("java.ext.dirs"));
        System.out.println("java.class.path(应用程序类加载器加载路径)------" + System.getProperty("java.class.path"));
    }

}
