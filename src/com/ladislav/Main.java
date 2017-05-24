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
        Philosopher socrates = new Philosopher("Socrates", fork2, fork3);
        Philosopher diogenes = new Philosopher("Diogenes", fork3, fork4);
        Philosopher plato = new Philosopher("Plato", fork4, fork1);

        heraclitus.start();
        socrates.start();
        diogenes.start();
        plato.start();
    }

}

class Philosopher extends Thread {

    private final Fork left;
    private final Fork right;
    private final String name;
    private boolean ate;

    public Philosopher(String name, Fork left, Fork right) {
        super();
        this.name = name;
        this.left = left;
        this.right = right;
    }

    public void eat() {
        try {
            Thread.sleep(50);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (!ate) {

            if (!left.isTaken()) {
                left.take();
                if (!right.isTaken()) {
                    right.take();
                    eat();
                    System.out.println(name + " ate.");
                    ate = true;
                    right.putDown();
                }
                left.putDown();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Fork {

    private boolean isTaken = false;

    public synchronized void take() {
        isTaken = true;
    }

    public synchronized void putDown() {
        isTaken = false;
    }

    public synchronized boolean isTaken() {
        return isTaken;
    }

}
