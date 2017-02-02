/**
 * 
 */
package com.cubestack.social.twitter.list;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubestack.social.candidate.TwitterUserCandidate;
import com.cubestack.social.model.TwitterUser;
import com.cubestack.social.repo.PersistantService;

import twitter4j.Status;

import com.cubestack.social.model.Tweet;
import com.cubestack.social.model.TweetList;

/**
 * @author Supal Dubey
 *
 */
@Service
@Transactional
public class TweetListService {

	@Autowired
	private PersistantService persistantService;

	
	public void addTweetToList(String category, Status interactionStatus, TwitterUserCandidate candidate) {
		TwitterUser tweeple = find(candidate);
		TweetList tweetList = find(category.toUpperCase(), tweeple);

		Tweet tweet = new Tweet();
		tweet.setText(candidate.getStatus().getText());
		tweet.setTweetId(candidate.getStatus().getId());
		
		// In case we need to point out to interaction tweet (SOURCE)
		tweet.setInReplyToTweetId(interactionStatus.getId());
		
		tweetList.getTweets().add(tweet);
		
		persistantService.saveTweep(tweeple);

	}
	
	public List<Tweet> findTweets(long userId, String category) {
		TwitterUser twitterUser = persistantService.findTweepByTwitterId(userId);
		
		for(TweetList list: twitterUser.getTweetLists()) {
			if(category.equalsIgnoreCase(list.getName())) {
				return list.getTweets();
			}
		}
		return null;
	}
	

	private TweetList find(String category, TwitterUser twitterUser) {

		TweetList list = null;
		List<TweetList> tweetLists = twitterUser.getTweetLists();

		if (tweetLists == null || tweetLists.isEmpty()) {
			List<TweetList> tList = new LinkedList<>();

			list = new TweetList();
			list.setName(category);
			tList.add(list);
			twitterUser.setTweetLists(tList);
		} else {
			for (TweetList tweetList : tweetLists) {
				if (category.trim().toUpperCase().equals(tweetList.getName())) {
					list = tweetList;
					break;
				}
			}

			if (list == null) {
				list = new TweetList();
				list.setName(category);
				tweetLists.add(list);
			}
		}

		return list;
	}

	public TwitterUser find(TwitterUserCandidate candidate) {
		TwitterUser tweeple = persistantService.findTweepByTwitterId(candidate.getTwitterId());
		// Save Tweep
		if (tweeple == null) {
			tweeple = new TwitterUser();
			tweeple.setName(candidate.getScreenName());
			tweeple.setScreenName(candidate.getScreenName());
			tweeple.setTwitterId(candidate.getTwitterId());
			
			tweeple.setTweetLists(new LinkedList<TweetList>());
		}
		return tweeple;
	}
}