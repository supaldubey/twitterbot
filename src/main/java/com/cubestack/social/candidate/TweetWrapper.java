/**
 * 
 */
package com.cubestack.social.candidate;

import java.util.LinkedList;
import java.util.List;

import com.cubestack.social.model.Tweet;

/**
 * @author Supal Dubey
 *
 */
public class TweetWrapper {

	private List<Tweet> tweets = new LinkedList<>();
	private String listName;
	private int totalPages;
	
	public List<Tweet> getTweets() {
		return tweets;
	}
	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
