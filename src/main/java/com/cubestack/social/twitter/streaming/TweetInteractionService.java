/**
 * 
 */
package com.cubestack.social.twitter.streaming;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${ui.base.url}")
	private String baseUrl;
	
	public void sendTweetTo(Status root, String text, File entity) throws TwitterException {
		StatusUpdate statusUpdate = new StatusUpdate(text);

		if (root != null) {
			// Add screen name to make sure twitter acknowledges it as a reply
			statusUpdate = new StatusUpdate("@" + root.getUser().getScreenName() + " " + text);
			statusUpdate.setInReplyToStatusId(root.getId());
		}

		// Attach the image
		if (entity != null) {
			statusUpdate.setMedia(entity);
		}

		twitter.updateStatus(statusUpdate);
	}
	
	public void sendConfirmation(Status root, String text) throws TwitterException {
		sendTweetTo(root, text + ": " + baseUrl + root.getUser().getScreenName(), null);
	}

	public DirectMessage sendDirectMsg(String text, String userId) throws TwitterException {
		return twitter.sendDirectMessage(userId, text);
	}

	public void deleteStatus(long statusId) throws TwitterException {
		twitter.destroyStatus(statusId);
	}

}
