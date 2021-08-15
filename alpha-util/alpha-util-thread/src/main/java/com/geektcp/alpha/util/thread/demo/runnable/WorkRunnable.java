package com.geektcp.alpha.util.thread.demo.runnable;

/* Created by Haiyang on 2017/3/16. */


class WorkRunnable implements Runnable {
    public WorkRunnable(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行     " + i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        WorkRunnable h1 = new WorkRunnable("线程A");
        Thread demo = new Thread(h1);
        WorkRunnable h2 = new WorkRunnable("线程Ｂ");
        Thread demo1 = new Thread(h2);
        demo.start();
        demo1.start();
    }

    private String name;

}

