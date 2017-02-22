/**
 * 
 */
package com.cubestack.social.async;

/**
 * @author Supal Dubey
 *
 */
public interface Task {

	public void perform();

	public void handleException(Exception e);
}
