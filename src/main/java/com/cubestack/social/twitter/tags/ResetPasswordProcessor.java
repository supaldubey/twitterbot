/**
 * 
 */
package com.cubestack.social.twitter.tags;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cubestack.social.model.TwitterUser;
import com.cubestack.social.persistance.TwitterUserRepository;
import com.cubestack.social.twitter.streaming.TweetInteractionService;
import com.cubestack.social.util.PasswordGenerator;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * @author Supal Dubey
 *
 */
@Component
public class ResetPasswordProcessor extends PasswordProcessor {

	private static final String RESET_PASSWORD = "RESET";

	private final TwitterUserRepository userRepository;

	private final TweetInteractionService tweetInteractionService;

	public ResetPasswordProcessor(TwitterUserRepository userRepository, TweetInteractionService tweetInteractionService) {
		super(userRepository, tweetInteractionService);
		this.userRepository = userRepository;
		this.tweetInteractionService = tweetInteractionService;
	}

	@Override
	public void handle(Status interactionStatus, Status status) {
		long twitterId = interactionStatus.getUser().getId();
		// Technically there should be only one.
		List<TwitterUser> users = userRepository.findByTwitterId(twitterId);

		if (users != null && !users.isEmpty()) {
			// Send the password in DM
			TwitterUser user = users.get(0);
			// Change the password
			user.setPin(new String(PasswordGenerator.generatePswd()));
			userRepository.save(user);

			try {
				DirectMessage msg = tweetInteractionService.sendDirectMsg(
						"PIN reset, your new access password is " + user.getPin(),
						interactionStatus.getUser().getScreenName());
				if (msg == null) {
					sendFailureTweet(interactionStatus, user);
				}
			} catch (TwitterException exception) {
				Logger.getLogger(ResetPasswordProcessor.class).error("Error resetting", exception);
				sendFailureTweet(interactionStatus, user);
			}
		}
	}

	@Override
	public String tag() {
		return RESET_PASSWORD;
	}

}
