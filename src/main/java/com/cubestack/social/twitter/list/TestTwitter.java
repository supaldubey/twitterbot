/**
 * 
 */
package com.cubestack.social.twitter.list;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterFactory;

/**
 * @author Supal Dubey
 *
 */
public class TestTwitter {

	public void main() {
		twitter4j.Twitter twitter = new TwitterFactory().getInstance();
		try {
			Query query = new Query("#demonatization");
			QueryResult result;
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());

				}
			} while ((query = result.nextQuery()) != null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
