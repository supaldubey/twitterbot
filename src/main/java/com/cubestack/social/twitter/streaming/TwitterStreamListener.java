/**
 * 
 */
package com.cubestack.social.twitter.streaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.twitter.tags.TwitterStatusProcessor;

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

	@Autowired
	private TwitterStatusProcessor statusProcessor;

	@Override
	public void onException(Exception ex) {
		ex.printStackTrace();
	}

	@Override
	public void onStatus(final Status status) {
		System.out.println(status.getUser().getName() + " : " + status.getText());

		Status processingStatus = null;

		if (status.getInReplyToStatusId() > 0) {
			try {
				processingStatus = twitter.showStatus((status.getInReplyToStatusId()));
				if (processingStatus == null) { //
					// don't know if needed - T4J docs are very bad
				} else {
					System.out.println("Nested Twter");
					System.out.println(
							"@" + processingStatus.getUser().getScreenName() + " - " + processingStatus.getText());
				}
			} catch (TwitterException e) {
				e.printStackTrace();

			}
		} else if (status.getQuotedStatus() != null) {
			processingStatus = status.getQuotedStatus();
		}
		// process only if we have valid status
		if (processingStatus != null) {
			statusProcessor.processStatus(status, processingStatus);
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
