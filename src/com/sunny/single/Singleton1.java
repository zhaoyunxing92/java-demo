package single;

/**
 * @author sunny
 * @className single.Singleton1
 * @date 2017-07-28 09:54
 * @description: 不用加锁也能实现懒加载的单例模式
 * //写一个内部类实现
 */
public class Singleton1 {
    private Singleton1() {
        System.out.println("single");
    }

    private static class Tnner {
        private static Singleton1 single = new Singleton1();
    }

    public static Singleton1 getInstance() {
        return Tnner.single;
    }
}
