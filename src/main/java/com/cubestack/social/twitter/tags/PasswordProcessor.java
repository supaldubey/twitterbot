/**
 * 
 */
package com.cubestack.social.twitter.tags;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.model.TwitterUser;
import com.cubestack.social.persistance.TwitterUserRepository;
import com.cubestack.social.twitter.streaming.TweetInteractionService;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * @author Supal Dubey
 *
 */
@Component
public class PasswordProcessor extends BaseTagProcessor {

	private static final String PASSWORD = "PASSWORD";

	@Autowired
	private TwitterUserRepository userRepository;

	@Autowired
	private TweetInteractionService tweetInteractionService;

	@Override
	public void handle(Status interactionStatus, Status status) {
		long twitterId = interactionStatus.getUser().getId();
		// Technically there should be only one.
		List<TwitterUser> users = userRepository.findByTwitterId(twitterId);

		if (users != null && !users.isEmpty()) {
			// Send the password in DM
			TwitterUser user = users.get(0);
			try {
				DirectMessage msg = tweetInteractionService.sendDirectMsg("Your access password is " + user.getPin(),
						interactionStatus.getUser().getScreenName());
				if (msg == null) {
					sendFailureTweet(interactionStatus, user);
				}
			} catch (TwitterException e) {
				e.printStackTrace();
				sendFailureTweet(interactionStatus, user);
			}
		}
	}

	protected void sendFailureTweet(Status interactionStatus, TwitterUser user) {
		try {
			tweetInteractionService.sendTweetTo(interactionStatus,
					"Unable to send PIN via direct message. If you are not following me, follow me Maybe?", null);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String tag() {
		return PASSWORD;
	}

}
