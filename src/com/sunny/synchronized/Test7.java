import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 * @className PACKAGE_NAME.Test7
 * @date 2017-07-25 13:30
 * @description: 子类调用父类的同步方法
 */
public class Test7 {
    synchronized void method1() {
        System.out.println("m1 start..");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end");
    }

    public static void main(String[] args) {
        new Test().method1(); //这里锁定的是new出来的this对象
    }
}

class Test extends Test7 {
    @Override
    synchronized void method1() {
        System.out.println("child start..");
        super.method1();    //调用父类的同步方法
        System.out.println("child end");
    }
}
