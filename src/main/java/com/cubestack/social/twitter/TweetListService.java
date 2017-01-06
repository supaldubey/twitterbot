/**
 * 
 */
package com.cubestack.social.twitter;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cubestack.social.candidate.TweepleCandidate;
import com.cubestack.social.model.TwitterUser;
import com.cubestack.social.model.Tweet;
import com.cubestack.social.model.TweetList;

/**
 * @author Supal Dubey
 *
 */
@Service
public class TweetListService {

	@Autowired
	private PersistantService persistantService;

	public void addTweetToList(String category, TweepleCandidate candidate) {
		TwitterUser tweeple = find(candidate);
		TweetList tweetList = find(category.toLowerCase(), tweeple);

		Tweet tweet = new Tweet();
		tweet.setInReplyToTweetId(candidate.getStatus().getInReplyToStatusId());
		tweet.setInReplyToUserId(candidate.getStatus().getInReplyToUserId());

	}

	private TweetList find(String category, TwitterUser tweeple) {

		TweetList list = null;
		List<TweetList> tweetLists = tweeple.getTweetLists();

		if (tweetLists == null || tweetLists.isEmpty()) {
			List<TweetList> tList = new LinkedList<>();

			list = new TweetList();
			list.setName(category);
			tList.add(list);
			tweeple.setTweetLists(tList);
		} else {
			for (TweetList tweetList : tweetLists) {
				if (category.trim().toLowerCase().equals(tweetList.getName())) {
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

	public TwitterUser find(TweepleCandidate candidate) {
		TwitterUser tweeple = persistantService.findTweepByTwitterId(candidate.getTwitterId());
		// Save Tweep
		if (tweeple == null) {
			tweeple = new TwitterUser();
			tweeple.setName(candidate.getScreenName());
			tweeple.setScreenName(candidate.getScreenName());
			tweeple.setTwitterId(candidate.getTwitterId());

			// Save this
			tweeple = persistantService.saveTweep(tweeple);
		}
		return tweeple;
	}
}
