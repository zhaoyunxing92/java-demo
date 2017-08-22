# 并发容器-List
> List区别
* CopyOnWriteArrayList
  >原理：在添加/修改时，先对当前容器复制一份，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器
  
  *写的速度特别慢，但是读的速度特别快，同时又是线程安全
  ```java
           /**
            * Appends the specified element to the end of this list.
            *
            * @param e element to be appended to this list
            * @return {@code true} (as specified by {@link Collection#add})
            */
           public boolean add(E e) {
               final ReentrantLock lock = this.lock;
               lock.lock();
               try {
                   Object[] elements = getArray();
                   int len = elements.length;
                   Object[] newElements = Arrays.copyOf(elements, len + 1);
                   newElements[len] = e;
                   setArray(newElements);
                   return true;
               } finally {
                   lock.unlock();
               }
           }
  ```
* ArrayList
  *线程不安全，但是效率高
*Vector
  *读写都加了锁，线程安全，但是速度慢 
  ```java
         
              /**
               * Appends the specified element to the end of this Vector.
               *
               * @param e element to be appended to this Vector
               * @return {@code true} (as specified by {@link Collection#add})
               * @since 1.2
               */
              public synchronized boolean add(E e) {
                  modCount++;
                  ensureCapacityHelper(elementCount + 1);
                  elementData[elementCount++] = e;
                  return true;
              }
  ```

  

