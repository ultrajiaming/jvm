package com.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ultrajiaming
 * @since 2020/8/29 10:35
 * 自定义类加载器测试
 */
public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader("E:/data/test");
        Class<?> aClass = myClassLoader.loadClass("com.jvm.User");
        Object object = aClass.newInstance();
        Method hello = aClass.getDeclaredMethod("hello", String.class);
        hello.invoke(object, "ultrajiaming");
        System.out.println(aClass.getClassLoader());
    }

    /**
     *  自定义类加载器
     **/
    static class MyClassLoader extends ClassLoader {
        //定义一个.class文件的位置，通过有参构造传入进来
        private String classPath;
        /**
         * 会调用父类的构造方法，设置该自定义类加载器的父类加载器为{@link sun.misc.Launcher.AppClassLoader}
         *  {@link ClassLoader#ClassLoader()}
         **/
        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        /**
         * 重写父类的{@link ClassLoader#findClass(String)}方法
         * @param name
         * @return java.lang.Class<?>
         **/
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            String path = name.replaceAll("\\.", "/");
            Class<?> result = null;
            try {
                byte[] data = loadByte(classPath + "/" + path + ".class");
                // 该方法是一个native方法，通过传入一个字节数组返回一个Class对象
                result = defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
            return result;
        }

        /**
         *  传入.class文件的全路径名，将其加载为字节数组返回
         * @param fileFullName
         * @return byte[]
         **/
        private byte[] loadByte(String fileFullName) throws Exception{
            FileInputStream fileInputStream = new FileInputStream(new File(fileFullName));
            byte[] result = new byte[fileInputStream.available()];
            fileInputStream.read(result);
            fileInputStream.close();
            return result;
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                long t0 = System.nanoTime();
                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();

                    if (name.startsWith("java.")) {
                        c = super.loadClass(name);
                    } else {
                        c = findClass(name);
                    }

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
                return c;
            }
        }
    }

}
