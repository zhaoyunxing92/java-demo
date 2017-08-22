# 并发容器-map
> map区别
* `ConcurrentHashMap`
  * ConcurrentHashMap使用(Segment[分片])锁机制。每个段其实就是一个小的`hashTable`，它们有自己的锁。只要多个修改操作发生在不同的段上，它们就可以并发进行。同样当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。
  * 存效率高
* `ConcurrentSkipListMap`
  * key有顺序
  * 插入数据慢，获取数据快
* `Hashtable`
  * HashTable不允许有`null`值的存在
  * HashTable中调用put方法时，如果key为`null`，直接抛出`NullPointerException`
  * HashTable是同步的，效率很低
  * sychronized意味着在一次仅有一个线程能够更改Hashtable。就是说任何线程要更新Hashtable时要首先获得同步锁，其它线程要等到同步锁被释放之后才能再次获得同步锁更新Hashtable。
  * hashTable初始化大小 11
   ```java
         /** lin 210
          * Constructs a new, empty hashtable with a default initial capacity (11)
          * and load factor (0.75).
          */
         public Hashtable() {
             this(11, 0.75f);
         }
   ```
* `HashMap`
  * HashMap是非线程安全的
  * HashMap的键和值都`允许`有null值存在，而HashTable则不行。
  * 因为线程安全的问题，HashMap效率比HashTable的要高
  * 实现同步方法
  > Map m = Collections.synchronizeMap(hashMap);
  * hashMap 初始化 16
  ```java
      /**
       * Constructs an empty <tt>HashMap</tt> with the default initial capacity
       * (16) and the default load factor (0.75).
       */
      public HashMap() {
          this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
      }
  ```
