/**
 * 
 */
package com.cubestack.social.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * @author Supal Dubey
 *
 */
@Component
public class TwitterStreamListener implements StatusListener {
	
	@Autowired
	private Twitter twitter;

	@Override
	public void onException(Exception ex) {

	}

	@Override
	public void onStatus(Status status) {
		System.out.println(status.getUser().getName() + " : " + status.getText());

		if (status.getInReplyToStatusId() > 0) {
			try {
				status = twitter.showStatus((status.getInReplyToStatusId()));
				if (status == null) { //
					// don't know if needed - T4J docs are very bad
				} else {
					System.out.println("Nested Twter");
					System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				}
			} catch (TwitterException e) {
				e.printStackTrace();

			}
		}
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {

	}

	@Override
	public void onStallWarning(StallWarning warning) {

	}

}
