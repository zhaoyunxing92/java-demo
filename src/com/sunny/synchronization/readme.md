# java-synchronized (原子操作不可分，可以保证原子性和可见性)
Java语言的关键字，当它用来修饰一个方法或者一个代码块的时候，能够保证在同一时刻最多只有一个线程执行该段代码.
* 当两个并发线程访问同一个对象object中的这个`synchronized(this)`同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块.
* 当一个线程访问object的一个`synchronized(this)`同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块.
* 当一个线程访问object的一个`synchronized(this)`同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞.
* 当一个线程访问object的一个`synchronized(this)`同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞.
* `synchronized` 锁定的不是代码块，而是一个`对象`
* `synchronized` 锁定的是**堆内存**的真实对象而不是栈内存的引用  [试例代码](/Test14.java)
### 锁
* 互斥锁：只能一个线程得到锁，最简单的就是`synchronized` 不公平锁
* 公平锁：根据线程等待的时间排序`等待时间长`的优先执行 (效率低，cpu要计算时间) [试例代码](../reentrantlock/Reentrantlock3.java)

### Volatile 关键字  [试例代码](/Test10.java)
* Volatile是`轻量级`的synchronized，它在多处理器开发中保证了共享变量的`可见性`. [试例代码](/Test10.java)
* volatile 不能代替synchronized,只能保证可见性.

### Atomicxxx类使用可以保证原子性  [试例代码](/Test11.java)
 * 可以保证原子性，但是不能保证多个方法连续调用的原子性. 
 * 假如在方法里面其他业务代码就有可能造成代码出现101的情况   [试例代码](/Test11.java) lin：19
 * AtomicInteger 源码
  ```java
  // AtomicInteger lin:68
   private volatile int value;
  
      /**
       * Creates a new AtomicInteger with the given initial value.
       *
       * @param initialValue the initial value
       */
      public AtomicInteger(int initialValue) {
          value = initialValue;
      }
```
### CountDownLatch (门闩)使用  [试例代码](/demo1/Container4.java)
  * `new CountDownLatch(1)` 初始化一个(门闩)
  * `latch.await()`  如果当前计数大于零，则当前线程将被禁用以进行线程调度，并处于休眠状态
  * `latch.countDown()` 减少锁存器的计数，如果计数达到零，释放所有等待的线程
  
### CyclicBarrier 使用   [试例代码](/demo1/Container5.java)
 * [java api](https://blog.fondme.cn/apidoc/jdk-1.8-google/)
 
### Semaphore 使用   [试例代码](/demo1/Container6.java)
  * [java api](https://blog.fondme.cn/apidoc/jdk-1.8-google/)



