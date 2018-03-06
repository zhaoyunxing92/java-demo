package synchronization;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test3
 * @date 2017-07-24 13:20
 * @description:  测试
 */
public class Test3 implements Runnable {
    private int count = 10;

    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + ">> count=" + count);
    }

    public static void main(String[] args) {
        Test3 t = new Test3();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "Thread" + i).start();
        }
    }
}
