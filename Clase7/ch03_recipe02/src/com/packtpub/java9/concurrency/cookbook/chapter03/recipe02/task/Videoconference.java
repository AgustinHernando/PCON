package com.packtpub.java9.concurrency.cookbook.chapter03.recipe02.task;

import java.util.concurrent.CountDownLatch;

public class Videoconference implements Runnable {

	private final CountDownLatch controller;

	public Videoconference(int number) {
		controller=new CountDownLatch(number);
	}

	public void arrive(String name){
		System.out.printf("%s has arrived.\n",name);
		// This method uses the countDown method to decrement the internal counter of the CountDownLatch
		controller.countDown();
		System.out.printf("VideoConference: Waiting for %d participants.\n",controller.getCount());
	}

	@Override
	public void run() {
		System.out.printf("VideoConference: Initialization: %d participants.\n",controller.getCount());
		try {
			// Wait for all the participants
			controller.await();
			// Starts the conference
			System.out.printf("VideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
