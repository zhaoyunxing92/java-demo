/**
 * @author sunny
 * @className PACKAGE_NAME.Test2
 * @date 2017-07-23 15:19
 * @description:将当前对象作为锁 , 该方法在执行的时候要先new出来，static的方法不能使用使用锁
 * <p>
 *    静态方法和非静态方法锁的区别：
 *  * 静态方法没有this概念，锁定的是当前类的对象
 *  * 非静态方法锁定的当前方法（this）对象
 * </p>
 */
public class Test2 {
    private static int count = 10;

    public void method() {
        synchronized (this) {   //任何要执行下面的代码都要先得到this的锁 （互斥锁）
            count--;
            System.out.println(Thread.currentThread().getName() + ":count>>>  " + count);
        }
    }

    //第二种写法
    public synchronized void method2() {//等同于 synchronized (this) ,锁定的是this对象(锁定当前对象)

        count--;
        System.out.println(Thread.currentThread().getName() + ":count>>>  " + count);

    }
    //静态方法锁
    public synchronized static void method3() {  //锁定的是 Test2.class对象
        count--;
        System.out.println(Thread.currentThread().getName() + ":count>>>  " + count);
    }
}
