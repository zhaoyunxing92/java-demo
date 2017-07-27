ThreadLocal(线程局部变量)  [试例代码](../threadlocal/ThreadLocal2.java)
---------
 这个类提供`线程局部变量`。 这些变量与其正常的对应方式不同，因为访问一个的每个线程（通过其get或set方法）都有自己独立初始化的变量副本。 ThreadLocal实例通常是希望将状态与线程关联的类中的私有静态字段（例如，用户ID或事务ID）。
=====
### 常用方法
 * `get()` 返回当前线程的此线程局部变量的副本中的值。
 * `initialValue()` 返回此线程局部变量的当前线程的`初始值`
 * `remove()` 删除此线程局部变量的当前线程的值。
 * `set(T value)` 将当前线程的此线程局部变量的副本设置为指定的值
###  和synchronized区别
* ThreadLocal是使用`空间换时间`，synchronized是使用`时间换空间`，ThreadLocal效率会更高
* ThreadLocal是每个线程改变不让另外一个线程知道=>空间换时间
