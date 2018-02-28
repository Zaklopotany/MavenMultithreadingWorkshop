package xyz.zaklopotany.philosophia.PhilosopherLockInterface;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//Implementacja sposobu Maćka : D

public class ForkLockImpl {
	private int id;
	private Lock lock;

	public ForkLockImpl(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
	}

	public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {
		if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
			System.out.println(philosopher + " acquired " + state.toString() + this);
			return true;
		} else {
			return false;
		}

	}

	public void putDown(Philosopher philosopher, State state) {
		lock.unlock();
		System.out.println(philosopher + " put downn " + state.toString() + this);
	}

	@Override
	public String toString() {
		return " fork";
	}
}
