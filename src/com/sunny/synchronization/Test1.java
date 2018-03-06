package synchronization;
/**
 * @author sunny
 * @className PACKAGE_NAME.Test1
 * @date 2017-07-23 15:01
 * @description:  单独new一个对象作为锁
 */
public class Test1 {
    private int count = 10;
    private Object object = new Object();

    public void method() {
        synchronized (object) {   //任何要执行下面的代码都要先得到Object的锁 （互斥锁）
            count--;
            System.out.println(Thread.currentThread().getName()+":count>>>  "+count);
        }
    }

}
