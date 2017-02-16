/**
 * 
 */
package com.cubestack.social.web;

/**
 * @author Supal Dubey
 *
 */
public class Response {

	private boolean success;
	private String message;

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

}
