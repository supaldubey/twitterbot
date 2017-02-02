/**
 * 
 */
package com.cubestack.social.candidate;

import org.hibernate.validator.constraints.NotEmpty;

import twitter4j.Status;



/**
 * @author supal
 *
 */
public class TwitterUserCandidate {

	private long id;

	@NotEmpty(message ="Twitter Id is missing.")
	private long twitterId;

	@NotEmpty(message ="Twitter Screen name is missing.")
	private String screenName;
	
	private Status status;
	
	private int pin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(long twitterId) {
		this.twitterId = twitterId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
