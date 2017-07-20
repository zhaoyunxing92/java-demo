package com.sunny.thread;

/**
 * @author sunny
 * @className com.sunny.thread.CounterThread
 * @date 2017-07-20 16:13
 * @description: 多线程求和
 */
public class CounterThread extends Thread {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        int sun = 0;
        for (int i = 0; i < 10000000; i++) {
            sun += i;
        }
        Long end = System.currentTimeMillis();
        System.out.println("sun:" + sun + ",耗时：" + (end-start)+":end"+end+":start:"+start);
    }
    
}
