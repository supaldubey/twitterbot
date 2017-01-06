/**
 * 
 */
package com.cubestack.social.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Supal Dubey
 *
 */
@Entity
@Table(name="TWEET")
public class Tweet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="TWEET_TXT")
	private String text;
	
	@Column(name="TWEET_ID")
	private long tweetId;
	
	@Column(name="REPLY_TWEET")
	private long inReplyToTweetId;
	
	@Column(name="RPLY_USR_ID")
	private long inReplyToUserId;
	
	@Column(name="RPLY_USR_SCR")
	private long inReplyToUserName;

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

	public long getInReplyToUserId() {
		return inReplyToUserId;
	}

	public void setInReplyToUserId(long inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	public long getInReplyToUserName() {
		return inReplyToUserName;
	}

	public void setInReplyToUserName(long inReplyToUserName) {
		this.inReplyToUserName = inReplyToUserName;
	}
	
	
	

}
