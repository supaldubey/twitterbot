/**
 * 
 */
package com.cubestack.social.twitter.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

/**
 * @author Supal Dubey
 *
 */
@Service
public class AsyncWorker {
	// TODO: Think over.
	private ExecutorService executorService = Executors.newFixedThreadPool(3);

	public void submit(final Task task) {
		Future<Void> response = executorService.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				task.perform();
				return null;
			}
		});

		try {
			response.get();
		} catch (Exception e) {
			task.handleException(e);
		}
	}
}
