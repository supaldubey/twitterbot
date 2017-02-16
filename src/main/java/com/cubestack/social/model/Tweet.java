/**
 * 
 */
package com.cubestack.social.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Supal Dubey
 *
 */
@Entity
@Table(name = "TWEET")
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "TWEET_TXT")
	private String text;

	@Column(name = "TWEET_ID")
	private long tweetId;

	@Column(name = "REPLY_TWEET")
	private long inReplyToTweetId;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private TweetList list;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getInReplyToTweetId() {
		return inReplyToTweetId;
	}

	public void setInReplyToTweetId(long inReplyToTweetId) {
		this.inReplyToTweetId = inReplyToTweetId;
	}

	public long getTweetId() {
		return tweetId;
	}

	public void setTweetId(long tweetId) {
		this.tweetId = tweetId;
	}

	public TweetList getList() {
		return list;
	}

	public void setList(TweetList list) {
		this.list = list;
	}

}
