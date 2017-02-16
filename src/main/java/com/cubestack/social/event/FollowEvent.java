/**
 * 
 */
package com.cubestack.social.event;

import twitter4j.User;

/**
 * @author Supal Dubey
 *
 */
public class FollowEvent extends TwitterEvent {

	private User twitterUser;

	public FollowEvent() {
		setType("Follow");
	}

	public User getTwitterUser() {
		return twitterUser;
	}

	public void setTwitterUser(User twitterUser) {
		this.twitterUser = twitterUser;
	}

}
