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

        Thread diogenes = new Thread(() -> {

            synchronized (fork1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork2) {
    
                    fork1.eat();
                    fork2.eat();
                    System.out.println("Diogenes ate his macaroni.");

                }
            }
        });

        Thread heraclitus = new Thread(() -> {

            synchronized (fork1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork4) {

                    fork1.eat();
                    fork4.eat();
                    System.out.println("Heraclitus ate his macaroni.");
                }
            }
        });

        Thread plato = new Thread(() -> {

            synchronized (fork3) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork2) {

                    fork3.eat();
                    fork2.eat();
                    System.out.println("Plato ate his macaroni.");
                }
            }
        });

        Thread socrates = new Thread(() -> {

            synchronized (fork3) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork4) {

                    fork3.eat();
                    fork4.eat();
                    System.out.println("Socrates ate his macaroni.");
                }
            }
        });

        diogenes.start();
        heraclitus.start();
        socrates.start();
        plato.start();

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
