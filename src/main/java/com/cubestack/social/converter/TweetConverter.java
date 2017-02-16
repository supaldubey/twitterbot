/**
 * 
 */
package com.cubestack.social.converter;

import java.util.LinkedList;
import java.util.List;

import com.cubestack.social.candidate.TweetCandidate;
import com.cubestack.social.model.Tweet;

/**
 * @author Supal Dubey
 *
 */
public class TweetConverter {

    public static TweetCandidate convertToCandidate(Tweet tweet) {
	TweetCandidate candidate = new TweetCandidate();
	
	candidate.setId(tweet.getId());
	candidate.setStatusText(tweet.getText());
	candidate.setTweetId(tweet.getTweetId());
	
	return candidate;
    }
    
    public static List<TweetCandidate> convertToCandidates(List<Tweet> tweets) {
	List<TweetCandidate> candidates = new LinkedList<>();
	for(Tweet tweet: tweets) {
	    candidates.add(convertToCandidate(tweet));
	}
	return candidates;
    }
}
