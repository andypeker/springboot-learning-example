package com.spring.springboot;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Frankie Yang on 2019-10-17.
 */
public class Thread3Way {

    public static void main(String[] args) {

//        way1();
//        way2();
//        way3();
        way4();

    }

    public static void way1() {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();

        myThread1.start();
        myThread2.start();

        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 20; i++) {
            System.out.println("线程[" + threadName + "]运行开始,i = " + i + " time = " + new Date());
        }
    }


    public static void way2() {
        MyRunable myRunable = new MyRunable();

        new Thread(myRunable).start();
        new Thread(myRunable).start();

        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 20; i++) {
            System.out.println("线程[" + threadName + "]运行开始,i = " + i + " time = " + new Date());
        }

    }

    public static void way3() {
        // 创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new MyCallerTask());
        // 启动线程
        new Thread(futureTask).start();
        System.out.println("其它操作" + new Date());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }

        try {
            // 等待任务执行完，并获得任务执行完的结果
            String result = futureTask.get();
            System.out.println(result + "Future result" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void way4() {
        // 创建异步任务
        MyFutureTask<String> ft2 = new MyFutureTask(new MyCallerTask());

        // 启动线程
        new Thread(ft2).start();

        System.out.println("其它操作" + new Date());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }

        try {
            // 等待任务执行完，并获得任务执行完的结果
            String result = String.valueOf(ft2.get());
            System.out.println("\t\t----" + ft2.getClass().getName());
            System.out.println(result + "Future result" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}

//  继承，不能多继承
class MyThread extends Thread {
    @Override
    public void run() {
        String threadName = getName();
        for (int i = 0; i < 20; i++) {
            System.out.println("线程[" + threadName + "]运行开始,i = " + i + " time = " + new Date());
        }
    }
}

//  实现，可以多实现
class MyRunable implements Runnable {

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 20; i++) {
            System.out.println("线程[" + threadName + "]运行开始,i = " + i + " time = " + new Date());
        }
    }
}

class MyCallerTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("执行任务开始" + new Date());
        Thread.sleep(3000);
        System.out.println("执行任务结束" + new Date());
        return "hello" + new Date();
    }
}

class MyFutureTask<T> extends FutureTask {

    private String name;

    /**真正的任务*/
    private Callable task;

    public MyFutureTask(Callable c) {
        super(c);
        this.task = c;
        this.name = "MyFutureTask";
    }
}
