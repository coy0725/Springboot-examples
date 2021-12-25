package com.imooc.concurrency.core.konwledge.singleton;

/**
 * 描述：     懒汉式（线程不安全）
 */
public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            //多线程问题
            instance = new Singleton3();
        }
        return instance;
    }
}
