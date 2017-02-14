/**
 * 
 */
package com.cubestack.social.twitter.streaming;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * @author Supal Dubey
 *
 */
@Service
public class TweetInteractionService {

    @Autowired
    private Twitter twitter;

    public void sendTweet(Status root, String text, File entity) throws TwitterException {
	StatusUpdate statusUpdate = new StatusUpdate(text);

	if (root != null && root.getInReplyToStatusId() > 0) {
	    // Add screen name to make sure twitter acknowledges it as a reply
	    statusUpdate = new StatusUpdate("@" + root.getInReplyToScreenName() + " " + text);
	    statusUpdate.setInReplyToStatusId(root.getInReplyToStatusId());
	}

	// Attach the unsplsah image
	if (entity != null) {
	    statusUpdate.setMedia(entity);
	}

	twitter.updateStatus(statusUpdate);
    }
    
    public void sendTweetTo(Status root, String text, File entity) throws TwitterException {
	StatusUpdate statusUpdate = new StatusUpdate(text);

	if (root != null) {
	    // Add screen name to make sure twitter acknowledges it as a reply
	    statusUpdate = new StatusUpdate("@" + root.getUser().getScreenName() + " " + text);
	    statusUpdate.setInReplyToStatusId(root.getId());
	}

	// Attach the unsplsah image
	if (entity != null) {
	    statusUpdate.setMedia(entity);
	}

	twitter.updateStatus(statusUpdate);
    }

    public DirectMessage sendDirectMsg(String text, String userId) throws TwitterException {
	return twitter.sendDirectMessage(userId, text);
    }

}
