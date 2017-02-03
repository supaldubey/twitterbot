/**
 * 
 */
package com.cubestack.social.event;

/**
 * @author Supal Dubey
 *
 */
public class TwitterEvent {
    
    private long twitterId;
    private String twitterScreenName;
    private String message;
    private String type;
    
    public long getTwitterId() {
        return twitterId;
    }
    public void setTwitterId(long twitterId) {
        this.twitterId = twitterId;
    }
    public String getTwitterScreenName() {
        return twitterScreenName;
    }
    public void setTwitterScreenName(String twitterScreenName) {
        this.twitterScreenName = twitterScreenName;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getType() {
	return type;
    }
    public void setType(String type) {
	this.type = type;
    }
    
}
