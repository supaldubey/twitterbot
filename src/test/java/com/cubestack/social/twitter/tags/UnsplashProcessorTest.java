/**
 * 
 */
package com.cubestack.social.twitter.tags;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.cubestack.social.async.AsyncWorker;
import com.cubestack.social.async.Task;
import com.cubestack.social.twitter.StatusUtils;
import com.cubestack.social.unsplash.UnsplashImageTask;
import com.cubestack.social.unsplash.UnsplashTagProcessor;


/**
 * @author Supal Dubey
 *
 */
public class UnsplashProcessorTest {
	private UnsplashTagProcessor processor;
	private MockAsyncWorker asyncWorker;
	
	@Before
	public void init () {
		processor = new UnsplashTagProcessor();
		asyncWorker = new MockAsyncWorker();
		ReflectionTestUtils.setField(processor, "asyncWorker", asyncWorker);
	}
	
	@Test
	public void testSetUp () {
		Assert.assertNotNull(processor);
	}
	
	@Test
	public void testMocks () {
		Assert.assertNotNull(ReflectionTestUtils.getField(processor, "asyncWorker"));
	}
	
	@Test
	public void testEmptyStatus () {
		processor.handle(StatusUtils.create("@cubestackbot #Unsplash"), null);
		Task task = asyncWorker.getTask();
		
		Assert.assertNotNull(task);
		Assert.assertTrue(task instanceof UnsplashImageTask);
		String searchText = (String) ReflectionTestUtils.getField(task, "search");
		Assert.assertTrue(searchText == null || searchText.length() == 0);
	}
	
	@Test
	public void testSpacesStatus () {
		processor.handle(StatusUtils.create("@cubestackbot #Unsplash               "), null);
		Task task = asyncWorker.getTask();
		
		Assert.assertNotNull(task);
		Assert.assertTrue(task instanceof UnsplashImageTask);
		String searchText = (String) ReflectionTestUtils.getField(task, "search");
		Assert.assertTrue(searchText == null || searchText.length() == 0);
	}
	
	@Test
	public void testCategoryStatus () {
		processor.handle(StatusUtils.create("@cubestackbot #Unsplash  nature"), null);
		Task task = asyncWorker.getTask();
		
		Assert.assertNotNull(task);
		Assert.assertTrue(task instanceof UnsplashImageTask);
		String searchText = (String) ReflectionTestUtils.getField(task, "search");
		Assert.assertEquals(searchText.toLowerCase(), "nature");
	}
	
	class MockAsyncWorker extends AsyncWorker {
		
		private Task task;
		
		@Override
		public void submit(final Task task) {
			this.task = task;
		}
		
		public Task getTask () {
			return task;
		}
	}
}
