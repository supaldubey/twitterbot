/**
 * 
 */
package com.cubestack.social.web;

import java.util.List;

import com.cubestack.social.candidate.TweetCandidate;
import com.cubestack.social.candidate.TwitterUserCandidate;

/**
 * @author Supal Dubey
 *
 */
public class Response {

	private boolean success = true;
	private String message;
	private TwitterUserCandidate twitterUserCandidate;
	private List<TweetCandidate> tweetCandidates;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TwitterUserCandidate getTwitterUserCandidate() {
		return twitterUserCandidate;
	}

	public void setTwitterUserCandidate(TwitterUserCandidate twitterUserCandidate) {
		this.twitterUserCandidate = twitterUserCandidate;
	}

	public List<TweetCandidate> getTweetCandidates() {
		return tweetCandidates;
	}

	public void setTweetCandidates(List<TweetCandidate> tweetCandidates) {
		this.tweetCandidates = tweetCandidates;
	}

}
