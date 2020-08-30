package com.jvm;

/**
 * @author ultrajiaming
 * @since 2020/8/30 21:54
 *
 *  对象逃逸分析
 *
 *  JVM通过逃逸分析确定该对象不会被外部访问。如果不会逃逸可以将该对象在栈上分配内存，
 *  这样该对象所占用的内存空间就可以随栈帧出栈而销毁，就减轻了垃圾回收的压力
 *
 *  JVM对于这种情况可以通过开启逃逸分析参数(-XX:+DoEscapeAnalysis)来优化对象内存分配位置，使其通过标量替换优先分配在栈上(栈上分配)，
 *  JDK7之后默认开启逃逸分析，如果要关闭使用参数(-XX:-DoEscapeAnalysis)
 */
public class EscapeAnalysisSample {

    /**
     *  使用如下参数不会发生GC
     *  -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
     *  使用如下参数都会发生大量GC
     *  -Xmx10m -Xms10m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
     *  -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
     **/
    public static void main(String[] args) {
        for (int i = 0; i < 100000000; i++) {
            sample2();
        }
        System.out.println("\033[31m" + "栈上分配依赖于逃逸分析和标量替换" + "\033");
    }


    /**
     *  该User对象被返回，不能确定对象生命周期
     **/
    private static User sample1() {
        User user = new User();
        user.setName("ultrajiaming");
        return user;
    }

    /**
     *  user对象我们可以确定当方法结束这个对象就可以认为是无效对象了，
     *  对于这样的对象我们其实可以将其分配在栈内存里，让其在方法结束时跟随栈内存一起被回收掉
     **/
    private static void sample2() {
        User user = new User();
        user.setName("ultrajiaming");
    }

}
