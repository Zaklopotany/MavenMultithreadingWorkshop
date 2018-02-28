package xyz.zaklopotany.philosophia;

public class Philosopher implements Runnable {

	private int id;
	private Fork leftFork;
	private Fork rightFork;
	private int eatingCounter = 0;;
	private int thinkingCounter = 0;;

	public Philosopher(int id, Fork leftFork, Fork rightFork) {
		this.leftFork = leftFork;
		this.rightFork = rightFork;
		this.id = id;
	}

	public void run() {
		try {
			while (true) {
				// thinking
				thinkingCounter++;
				doAction(System.currentTimeMillis() + ": " + this + ": Thinking ");
				synchronized (leftFork) {
					doAction(System.currentTimeMillis() + ": " + this + ": Picked up leftFork");
					synchronized (rightFork) {
						doAction(System.currentTimeMillis() + ": " + this + ": Picked up RightFork - eating");
						doAction(System.currentTimeMillis() + ": " + this + ": Put down RightFork");
					}
					eatingCounter++;
					// back to thinking
					doAction(System.currentTimeMillis() + " " + this + " Put down left fork, back to thinking");
					System.out.println("" + this + "eated " + eatingCounter + " thought " + thinkingCounter);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void doAction(String action) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " " + action);
		Thread.sleep(((int) (Math.random() * 10)));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fork getLeftFork() {
		return leftFork;
	}

	public void setLeftFork(Fork leftFork) {
		this.leftFork = leftFork;
	}

	public Fork getRigthFork() {
		return rightFork;
	}

	public void setRigthFork(Fork rigthFork) {
		this.rightFork = rigthFork;
	}



	public Fork getRightFork() {
		return rightFork;
	}

	public void setRightFork(Fork rightFork) {
		this.rightFork = rightFork;
	}

	public int getEatingCounter() {
		return eatingCounter;
	}

	public void setEatingCounter(int eatingCounter) {
		this.eatingCounter = eatingCounter;
	}

	public int getThinkingCounter() {
		return thinkingCounter;
	}

	public void setThinkingCounter(int thinkingCounter) {
		this.thinkingCounter = thinkingCounter;
	}

	@Override
	public String toString() {
		return "Philosopher " + id;
	}

}
