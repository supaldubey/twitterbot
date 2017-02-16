/**
 * 
 */
package com.cubestack.social.candidate;

/**
 * @author Supal Dubey
 *
 */
public class TweetCandidate {

    private long id;
    private long tweetId;
    private String statusText;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTweetId() {
        return tweetId;
    }
    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }
    public String getStatusText() {
        return statusText;
    }
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
