/**
 * 
 */
package com.cubestack.social.async;

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
	private ExecutorService executorService;

	public void submit(final Task task) {
		Future<Void> response = service().submit(new Callable<Void>() {

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
	
	//Lazy initliazation
	private ExecutorService service() {
		if(executorService == null) {
			synchronized (this) {
				if(executorService == null) {
					executorService = Executors.newFixedThreadPool(3);
				}
			}
		}
		return executorService;
	}
}
