/**
 * 
 */
package com.cubestack.social.converter;

import com.cubestack.social.candidate.TwitterUserCandidate;
import com.cubestack.social.model.TweetList;
import com.cubestack.social.model.TwitterUser;

/**
 * @author Supal Dubey
 *
 */
public class TwitterUserConverter {

	public static TwitterUserCandidate convertToCandidate(TwitterUser twitterUser) {
		TwitterUserCandidate candidate = new TwitterUserCandidate();

		candidate.setId(twitterUser.getId());
		candidate.setScreenName(twitterUser.getScreenName());
		candidate.setTwitterId(twitterUser.getTwitterId());

		for (TweetList tweetList : twitterUser.getTweetLists()) {
			candidate.getTweetLists().add(tweetList.getName());
		}

		return candidate;
	}
}
