# 并发容器-Queue

### ConcurrentLinkedQueue
 #### 特点
  * 容器无大小
  * 单向队列
 #### 方法
  * `poll()` 获取最后一个并删除
  
  ```java
     public E poll() {
            restartFromHead:
            for (;;) {
                for (Node<E> h = head, p = h, q;;) {
                    E item = p.item;
    
                    if (item != null && p.casItem(item, null)) {
                      //成功的ca是线性化点
                      //从这个队列中删除项目
                        if (p != h) // hop two nodes at a time
                            updateHead(h, ((q = p.next) != null) ? q : p);
                        return item;
                    }
                    else if ((q = p.next) == null) {
                        updateHead(h, p);
                        return null;
                    }
                    else if (p == q)
                        continue restartFromHead;
                    else
                        p = q;
                }
            }
        }
  ```
  * `peek()` 只是偷窥，不删除数据
  ```java
   public E peek() {
          restartFromHead:
          for (;;) {
              for (Node<E> h = head, p = h, q;;) {
                  E item = p.item;
                  if (item != null || (q = p.next) == null) {
                      updateHead(h, p);
                      return item;
                  }
                  else if (p == q)
                      continue restartFromHead;
                  else
                      p = q;
              }
          }
      }
  ```
  * offer()  在容器尾部插入，成功返回true
  
### ConcurrentLinkedDeque
  #### 特点
   * 双端队列，可以在头/尾拿、添加
   * 其他跟  `ConcurrentLinkedQueue` 一样
   
### ArrayBlockingQueue
 #### 特点
  * 容器初始要设置大小，超出异常（`throw new IllegalStateException("Queue full")`）
  ```java
         /**
          * Creates an {@code ArrayBlockingQueue} with the given (fixed)
          * capacity and default access policy.
          *
          * @param capacity the capacity of this queue
          * @throws IllegalArgumentException if {@code capacity < 1}
          */
         public ArrayBlockingQueue(int capacity) {
             this(capacity, false);
         }
  ```
  * 可以设置公平锁,默认不公平
  ```java
         
    /**
     * Creates an {@code ArrayBlockingQueue} with the given (fixed)
     * capacity and the specified access policy.
     *
     * @param capacity the capacity of this queue
     * @param fair if {@code true} then queue accesses for threads blocked
     *        on insertion or removal, are processed in FIFO order;
     *        if {@code false} the access order is unspecified.
     * @throws IllegalArgumentException if {@code capacity < 1}
     */
    public ArrayBlockingQueue(int capacity, boolean fair) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        this.items = new Object[capacity];
        lock = new ReentrantLock(fair); //设置锁的公平
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }
  ```
  #### 方法
   * `offer()`  给容器添加数据，容器满时不会异常，只会返回true/flase
   * `add()`    给容器添加数据，容器满时会异常，
   * `put`      容器满了就阻塞
 
### DelayQueue
   #### 特点
   * 按照时间排序进/出(时间短的先出)
   * 类实现delayed接口

### TransferQueue
  #### 特点
   * 数据不进入容器、直接给消费者，速度会更快
   * transfer()时，没有消费者线程会阻塞

### SynchronousQueue
  #### 特点
   * 容器为0,也就是不能调用add()方法
   * 必须消费,也就是put()方法一直阻塞，直到消费者消费 
   * 内部也是使用TransferQueue
    
   ```java
        /**
         * Adds the specified element to this queue, waiting if necessary for
         * another thread to receive it.
         *
         * @throws InterruptedException {@inheritDoc}
         * @throws NullPointerException {@inheritDoc}
         */
        public void put(E e) throws InterruptedException {
            if (e == null) throw new NullPointerException();
            //立即转让，失败后异常
            if (transferer.transfer(e, false, 0) == null) {
                Thread.interrupted();
                throw new InterruptedException();
            }
        }
   ```  
## 总结
 > 阻塞队列
      
   * BlockingQueue 下的
    
       * ArrayBlockingQueue
       * LinkedBlockingQueue
       * TransferQueue
       * SynchronousQueue
   * DelayQueue
   
源码：[github](https://github.com/zhaoyunxing92/Java-demo) 欢迎各位同学指出问题/建议   
