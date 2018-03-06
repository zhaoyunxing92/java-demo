package synchronization;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test4
 * @date 2017-07-24 13:30
 * @description: 同步方法和非同步方法调用
 * <p>
 * 程序在运行时加锁方法不影响其他非同步方法
 * </p>
 */
public class Test4 {
    public static void main(String[] args) {
        Test4 t4 = new Test4();
        //java 1.8语法
        new Thread(()->t4.method1()).start();
        new Thread(()->t4.method2()).start();
     
    }

    /**
     * 同步方法
     */
    public synchronized void method1() {
        System.out.println("method1 start......");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method1 end......");
    }

    /**
     * 非同步方法
     */
    public void method2() {
        System.out.println("======= method2 start ========");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("======== method2 end ========");
    }
}
