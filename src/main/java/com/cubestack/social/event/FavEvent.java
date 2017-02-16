/**
 * 
 */
package com.cubestack.social.event;

import twitter4j.Status;

/**
 * @author Supal Dubey
 *
 */
public class FavEvent extends TwitterEvent {

	private Status status;

	public FavEvent() {
		setType("FAV");
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
