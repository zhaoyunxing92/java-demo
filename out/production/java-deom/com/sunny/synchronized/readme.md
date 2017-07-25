# java-synchronized (原子操作不可分)
Java语言的关键字，当它用来修饰一个方法或者一个代码块的时候，能够保证在同一时刻最多只有一个线程执行该段代码.
* 当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块.
* 当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块.
* 当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞.
* 当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞.
*  `synchronized` 锁定的不是代码块，而是一个`对象`
### 锁
* 互斥锁 只能一个线程得到锁，最简单的就是`synchronized`

### Volatile 关键字
* Volatile是`轻量级`的synchronized，它在多处理器开发中保证了共享变量的`可见性`.
* volatile 不能代替synchronized,只能保证可见性



