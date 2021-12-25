package com.imooc.concurrency.core.konwledge.threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.LinkedList;

/**
 * 描述：     用wait/notify来实现生产者消费者模式
 */
public class OddEvenPrint {
    public static void main(String[] args) {
        Lock lock = new Lock();
        Old old = new Old(lock);
        Even even = new Even(lock);
        new Thread(old).start();
        new Thread(even).start();

    }
}

class Old implements Runnable {

    private Lock storage;

    public Old(
            Lock storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.printOdd();
        }
    }
}

class Even implements Runnable {

    private Lock storage;

    public Even(
            Lock storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}

class Lock {

    private int maxSize;
    private LinkedList<Date> storage;



    public Lock() {
        maxSize = 1;
        storage = new LinkedList<>();
    }

    public synchronized void printOdd() {
        try {
            for (int i =0 ;i<100;i=i+2){
                System.out.println(Thread.currentThread().getName()+":"+i);
                // 调用wait方法不是释放了锁吗,q：释放锁之后还处在等待集中
                // 先notify另外一个处于等待集的线程，再wait
                //？ 为什么线程一直在运行？？
                notify();
                wait();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void take() {
        try {
            for (int i =1 ;i<100;i=i+2){
                System.out.println(Thread.currentThread().getName()+":"+i);
                notify();
                wait();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


