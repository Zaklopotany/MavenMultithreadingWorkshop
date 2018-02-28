package xyz.zaklopotany.philosophia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosophers {

	public static void main(String[] args) {
		Philosopher[] philosophers = new Philosopher[5];
		Fork[] forks = new Fork[philosophers.length];
		ExecutorService exe = Executors.newFixedThreadPool(philosophers.length);

		for (int i = 0; i < forks.length; i++) {
			forks[i] = new Fork(i);
		}

		for (int i = 0; i < philosophers.length; i++) {
			Fork leftFork = forks[i];
			Fork rightFork = forks[(i + 1) % forks.length];
			if(i == philosophers.length -1) {
				philosophers[i] = new Philosopher(i + 1, rightFork, leftFork);
				
			} else {
				philosophers[i] = new Philosopher(i + 1, leftFork, rightFork);
				
			}
			exe.execute(philosophers[i]);

		}

	}
}
