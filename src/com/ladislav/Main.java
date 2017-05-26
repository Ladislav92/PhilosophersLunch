package com.ladislav;

/**
 * Solution to Philosopher macaroni problem of thread deadlocks.
 */
public class Main {

    public static void main(String[] args) {
        Fork fork1 = new Fork(1);
        Fork fork2 = new Fork(2);
        Fork fork3 = new Fork(3);
        Fork fork4 = new Fork(4);

        Philosopher heraclitus = new Philosopher(4, "Heraclitus", fork1, fork2);
        Philosopher socrates = new Philosopher(3, "Socrates", fork2, fork3);
        Philosopher diogenes = new Philosopher(2, "Diogenes", fork3, fork4);
        Philosopher plato = new Philosopher(1, "Plato", fork4, fork1);

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
    private final int number;

    public Philosopher(int number, String name, Fork left, Fork right) {
        super();
        this.name = name;
        this.left = left;
        this.right = right;
        this.number = number;
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

        while (true) {

            if (this.number == left.getNumber()) {
                if (!left.isChecked()) {
                    synchronized (left) {
                        synchronized (right) {
                            eat();
                            right.check();
                        }
                    }
                }
            } else {
                if (left.isChecked()) {
                    synchronized (left) {
                        synchronized (right) {
                            eat();
                            right.checkOut();
                        }
                    }
                }
            }
            System.out.println(name + " ate.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Fork {

    private final int number;
    private boolean checked;

    public Fork(int number) {
        this.number = number;
    }

    public synchronized void check() {
        checked = true;
    }

    public synchronized void checkOut() {
        checked = false;
    }

    public synchronized boolean isChecked() {
        return checked;
    }

    public int getNumber() {
        return number;
    }

}
