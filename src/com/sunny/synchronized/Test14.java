/**
 * @author sunny
 * @className PACKAGE_NAME.Test14
 * @date 2017-07-26 10:14
 * @description: 在使用字符串常量作为锁定对象时，锁定的都是同一个字符串
 * 两个方法锁定的都是“java”字符串
 */
public class Test14 {
    String s1 = "java";
    String s2 = "java";

    void m1() {
        synchronized (s1) {

        }
    }

    void m2() {
        synchronized (s2) {

        }
    }
}
