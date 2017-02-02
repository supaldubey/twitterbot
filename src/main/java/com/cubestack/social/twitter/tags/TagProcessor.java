/**
 * 
 */
package com.cubestack.social.twitter.tags;

import twitter4j.Status;

/**
 * @author Supal Dubey
 *
 */
public interface TagProcessor {
	
	public void handle(Status interactionStatus, Status status);
	String tag();

}
