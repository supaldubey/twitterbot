/**
 * 
 */
package com.cubestack.social.twitter.tags;

import java.io.File;

import com.cubestack.social.persistance.TwitterUserPersistantService;
import com.cubestack.social.twitter.list.TweetService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.cubestack.social.candidate.TwitterUserCandidate;
import com.cubestack.social.twitter.StatusUtils;
import com.cubestack.social.twitter.list.TweetListService;
import com.cubestack.social.twitter.streaming.TweetInteractionService;

import twitter4j.Status;
import twitter4j.TwitterException;


/**
 * @author Supal Dubey
 *
 */
public class TwitterListProcessorTest {
	private TweetListProcessor processor;
	private MockTweetListService tweetListService;
	private MockTweetInteractionService tweetInteractionService;
	
	@Before
	public void init () {
		tweetInteractionService = new MockTweetInteractionService();
		tweetListService = new MockTweetListService(null, null);
		processor = new TweetListProcessor(tweetListService, tweetInteractionService);
	}
	
	@Test
	public void testSetUp () {
		Assert.assertNotNull(processor);
	}
	
	@Test
	public void testMocks () {
		Assert.assertNotNull(ReflectionTestUtils.getField(processor, "tweetListService"));
		Assert.assertNotNull(ReflectionTestUtils.getField(processor, "tweetInteractionService"));
	}
	
	@Test
	public void testEmptyStatus () {
		processor.handle(StatusUtils.create("@cubestackbot #list", "cubestack"), StatusUtils.create("Some awesome tweet on null", "supal_dubey"));
		Assert.assertTrue(tweetListService.getCategory() == null || tweetListService.getCategory().length() == 0);
	}
	
	@Test
	public void testSpacesStatus () {
		processor.handle(StatusUtils.create("@cubestackbot #list               ", "cubestack"), StatusUtils.create("Some awesome tweet on null", "supal_dubey"));
		
		Assert.assertTrue(tweetListService.getCategory() == null || tweetListService.getCategory().length() == 0);
		
	}
	
	@Test
	public void testCategoryStatus () {
		processor.handle(StatusUtils.create("@cubestackbot #list  TODO", "cubestack"), StatusUtils.create("Some awesome tweet on TODO", "supal_dubey"));
		
		Assert.assertEquals(tweetListService.getCategory(), "TODO");
	}
	
	@Test
	public void testCategoryStatusSpaces () {
		processor.handle(StatusUtils.create("@cubestackbot #lISt          TODO           ", "cubestack"), StatusUtils.create("Some awesome tweet on TODO", "supal_dubey"));
		
		Assert.assertEquals(tweetListService.getCategory(), "TODO");
	}
	
	class MockTweetListService extends TweetListService {

		private String category;
		private String userScreenName;

		public MockTweetListService(TwitterUserPersistantService persistantService, TweetService tweetService) {
			super(persistantService, tweetService);
		}

		public void addTweetToList(String category, Status interactionStatus, TwitterUserCandidate candidate) {
			this.category = category;
			this.userScreenName = candidate.getScreenName();
		}
		
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getUserScreenName() {
			return userScreenName;
		}
		public void setUserScreenName(String userScreenName) {
			this.userScreenName = userScreenName;
		}
	}
	
	class MockTweetInteractionService extends TweetInteractionService {
		
		@Override
		public void sendConfirmation(Status root, String text) throws TwitterException {
			Assert.assertNotNull(text);
		}
		
		@Override
		public void sendTweetTo(Status root, String text, File entity) throws TwitterException {
			Assert.assertNotNull(text);
		}
	}
}
