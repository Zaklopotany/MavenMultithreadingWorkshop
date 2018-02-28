package xyz.zaklopotany.philosophia.PhilosopherLockInterface;

import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {

	private int id;
	private ForkLockImpl leftFork;
	private ForkLockImpl rightFork;
	private boolean isHungry = false;
	private int thinkingCounter = 0;
	private int eatingCounter = 0;

	public Philosopher(int id, ForkLockImpl leftFork, ForkLockImpl rightFork) {
		this.id = id;
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	public void run() {
		while (true) {
			
			try {
				think();

				while (isHungry) {
					if (leftFork.pickUp(this, State.LEFT)) {
						if (rightFork.pickUp(this, State.RIGHT)) {
							eat();
							rightFork.putDown(this, State.RIGHT);
						}
						leftFork.putDown(this, State.LEFT);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void think() throws InterruptedException {
		System.out.println(this + " is thinking");
		Thread.sleep(((int) Math.random() * 1000));
		thinkingCounter++;
		if (((int) (Math.random() * 10)) >= 5) {
			setHungry(true);
		}
	}

	public void eat() throws InterruptedException {
		System.out.println(this + " is eating");
		eatingCounter++;
		TimeUnit.MILLISECONDS.sleep(10);
		setHungry(false);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ForkLockImpl getLeftFork() {
		return leftFork;
	}

	public void setLeftFork(ForkLockImpl leftFork) {
		this.leftFork = leftFork;
	}

	public ForkLockImpl getRightFork() {
		return rightFork;
	}

	public void setRightFork(ForkLockImpl rightFork) {
		this.rightFork = rightFork;
	}

	public boolean isHungry() {
		return isHungry;
	}

	public void setHungry(boolean isHungry) {
		this.isHungry = isHungry;
	}

	@Override
	public String toString() {
		return " Philosopher " + id;
	}
}
