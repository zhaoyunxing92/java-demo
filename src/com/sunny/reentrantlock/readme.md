 ReentrantLock代替Synchronized
--- 
### 区别
|        |   ReentrantLock   |  Synchronized  |
| -----: | :-----:           |    :----:      |
| 锁类型  | 公平锁/非公平锁 |   非公平锁     |
| 可中断   |   是   |   否   |
| 多锁绑多个        |    是    |  否  |
| 自动释放        |    否    |  是  |
| 有异常释放        |    否    |  是  |

### 常用方法
 * `unlock()` 尝试释放此锁。
 * `tryLock()` 只有在调用时它不被另一个线程占用才能获取锁。
 * `tryLock(long timeout, TimeUnit unit)` 如果在给定的等待时间内没有被另一个线程 占用 ，并且当前线程尚未被 保留，则获取该锁（ interrupted） 
 * `lockInterruptibly()` 如果锁没有被另一个线程占用并且立即返回，则将锁定计数设置为1。 (可以被打断)
 * `lock()`  获得锁。(不可以打断)
 * `getHoldCount()` 查询当前线程对此锁的暂停数量。
 * `unlock()` 释放锁
 * `isLocked()` 查询此锁是否由任何线程持有




