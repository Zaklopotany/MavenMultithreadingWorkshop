package xyz.zaklopotany.philosophia.PhilosopherLockInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosophers {

	public static int NUM_OF_PHILOSOPHERS = 5;
	public static int NUM_OF_FORKS = 5;
	
	public static void main(String[] args) {
		Philosopher[] philosophers = new Philosopher[NUM_OF_PHILOSOPHERS];
		ForkLockImpl[] forks = new ForkLockImpl[NUM_OF_FORKS];
		ExecutorService exec = Executors.newFixedThreadPool(NUM_OF_PHILOSOPHERS);
		
		for (int i = 0; i < NUM_OF_FORKS; i++) {
			forks[i] = new ForkLockImpl(i + 1);
		}

		for (int i = 0; i < NUM_OF_PHILOSOPHERS; i++) {
			philosophers[i] = new Philosopher(i + 1, forks[i], forks[(i + 1) % NUM_OF_FORKS]);
			exec.execute(philosophers[i]);
		}
		
		

	}
}
