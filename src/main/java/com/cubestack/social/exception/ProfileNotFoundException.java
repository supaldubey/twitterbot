/**
 * 
 */
package com.cubestack.social.exception;

/**
 * @author supal
 *
 */
public class ProfileNotFoundException extends Exception {

    private static final long serialVersionUID = -6261208952981195533L;

    public ProfileNotFoundException() {
	super("No such profile found");
    }

}
