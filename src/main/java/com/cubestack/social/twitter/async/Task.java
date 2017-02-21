/**
 * 
 */
package com.cubestack.social.twitter.async;

/**
 * @author Supal Dubey
 *
 */
public interface Task {

	public void perform();

	public void handleException(Exception e);
}
