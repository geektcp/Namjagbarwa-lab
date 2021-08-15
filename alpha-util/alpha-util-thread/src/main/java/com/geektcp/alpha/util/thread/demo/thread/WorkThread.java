package com.geektcp.alpha.util.thread.demo.thread;

/* Created by Haiyang on 2017/3/16. */



class WorkThread extends Thread {
    private String name;

    public WorkThread(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行\t" + i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        WorkThread h1 = new WorkThread("A");
        WorkThread h2 = new WorkThread("B");

        h1.start();
        System.out.println("==============");
        h2.start();

        try {
            h1.join();
            h2.join();
        }catch (Exception e){
            e.printStackTrace();
        }



//        h1.stop();
//        h2.stop();
    }

}
