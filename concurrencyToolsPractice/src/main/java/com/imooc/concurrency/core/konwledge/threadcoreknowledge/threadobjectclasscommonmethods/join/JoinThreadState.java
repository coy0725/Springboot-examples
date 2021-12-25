package com.imooc.concurrency.core.konwledge.threadcoreknowledge.threadobjectclasscommonmethods.join;

/**
 * 描述：     先join再mainThread.getState()
 * 通过debugger看线程join前后状态的对比
 */
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(mainThread.getName()+":"+mainThread.getState());
                    System.out.println(Thread.currentThread().getName()+":运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println(Thread.currentThread().getName()+":等待子线程运行完毕");
        thread.join();
        System.out.println(Thread.currentThread().getName()+":子线程运行完毕");

    }
}
