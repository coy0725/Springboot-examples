package com.imooc.concurrency.core.konwledge.background;


/**
 * 描述：     用工厂模式修复刚才的初始化问题
 */
public class EventListenerErrorFix {

    int count;
    private EventListener listener;

    private EventListenerErrorFix(MySource source) {
        listener = new EventListener() {
            @Override
            public void onEvent(EventListenerError.Event e) {
                System.out.println("\n我得到的数字是" + count);
            }

        };
        for (int i = 0; i < 100; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static EventListenerErrorFix getInstance(MySource source) {
        EventListenerErrorFix safeListener = new EventListenerErrorFix(source);
        source.registerListener(safeListener.listener);
        return safeListener;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new EventListenerError.Event() {
                });
            }
        }).start();
        EventListenerErrorFix eventListenerErrorFix = new EventListenerErrorFix(mySource);
    }

    static class MySource {

        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(EventListenerError.Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }

    }

    interface EventListener {

        void onEvent(EventListenerError.Event e);
    }

    interface Event {

    }
}
