import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test5
 * @date 2017-07-24 20:58
 * @description:
 * <p>
 *     业务代码对写方法加锁，对读方法不加上容易导致脏读
 * </p>
 */
public class Test5 {
    String name;
    double balance;//余额

    public synchronized void setBalance(String name, double balance) {
        this.name = name;
        //程序在休眠的时候,但是程序有可能已经调用 getBalance() 方法
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {
        Test5 test5 = new Test5();
        new Thread(() -> test5.setBalance("zhangsan", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test5.getBalance());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test5.getBalance());
    }
}
