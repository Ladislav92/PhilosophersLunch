package com.ladislav;

/**
 * Solution to Philosopher macaroni problem of thread deadlocks.
 */
public class Main {

    public static void main(String[] args) {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();

        Philosopher heraclitus = new Philosopher("Heraclitus", fork1, fork2);
        Philosopher socrates = new Philosopher("Socrates", fork2, fork1);
        Philosopher diogenes = new Philosopher("Diogenes", fork3, fork4);
        Philosopher plato = new Philosopher("Plato", fork4, fork1);

        heraclitus.start();
        socrates.start();
        diogenes.start();
        plato.start();
    }

}

class Philosopher extends Thread {

    private static final Object lock = new Object();
    private final Fork left;
    private final Fork right;

    public Philosopher(String name, Fork left, Fork right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {

        synchronized (lock) {
            synchronized (left) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (right) {
                    left.eat();
                    right.eat();
                    System.out.println(Thread.currentThread().getName() + " ate his macaroni.");
                }
            }
        }
    }
}

class Fork {

    public void eat() {
        try {
            Thread.sleep(50);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


