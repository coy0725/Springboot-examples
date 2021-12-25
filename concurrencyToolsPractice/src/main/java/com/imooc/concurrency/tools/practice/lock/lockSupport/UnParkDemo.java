package com.imooc.concurrency.tools.practice.lock.lockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author coy
 * @since 2021/8/31
 **/
public class UnParkDemo {
    public static void main(String[] args) {
        System.out.println("begin park");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("end park");
    }
}
