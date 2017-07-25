package thread.demo;

/**
 * @author sunny
 * @className com.sunny.thread.demo.ThreadTest1
 * @date 2017-07-20 17:26
 * @description: \n
 * java 两种实现线程的方法
 * 1.extends(继承)thread
 * 2.implements(实现)`Runnable`接口
 * <p>
 * 　要注意的是：
 * <p>
 * 　　　1.t1.run()并不是启动线程，而是简单的方法调用。
 * <p>
 * 　　　2.Thread也有run()方法，如果该线程是使用独立的 Runnable 运行对象构造的，则调用该 Runnable 对象的 run 方法；否则，该方法不执行任何操作并返回。
 * <p>
 * 　　　3.并不是一启动线程（调用start()方法）就执行这个线程，而是进入就绪状态，什么时候运行要看CUP。
 */
public class ThreadTest1 {


    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // MyThread.interrupted();
        //myThread.run();
        Thread t1 = new Thread(myThread, "线程1");
        Thread t2 = new Thread(myThread, "线程2");
        t1.start();
        t2.start();
        //t1.run();
    }

    //继承thread类
    static class MyThread extends Thread {
        public void run() {
            String name = Thread.currentThread().getName();
            String inf = Thread.currentThread().toString();
            long idnum = Thread.currentThread().getId();
            for (int i = 0; i < 10; i++) {
                System.out.println("i----------" + i + ",threadName==" + name
                        + ",threadId==" + idnum + ",threadInf==" + inf);
            }
        }
    }
}
