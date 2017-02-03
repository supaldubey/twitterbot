/**
 * 
 */
package com.cubestack.social.event;

/**
 * @author Supal Dubey
 *
 */
public class DirectMessageEvent extends TwitterEvent {

    public DirectMessageEvent () {
	setType("DM");
    }
    
}
